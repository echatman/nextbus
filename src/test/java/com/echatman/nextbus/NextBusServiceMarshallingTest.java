package com.echatman.nextbus;

import com.echatman.nextbus.request.PredictionsForMultiStopsRequest;
import com.echatman.nextbus.request.PredictionsRequest;
import com.echatman.nextbus.request.RouteConfigRequest;
import com.echatman.nextbus.response.agencylist.Agency;
import com.echatman.nextbus.response.agencylist.AgencyListResponse;
import com.echatman.nextbus.response.locations.VehicleLocationsResponse;
import com.echatman.nextbus.response.messages.MessagesResponse;
import com.echatman.nextbus.response.predictions.PredictionsForMultiStopsResponse;
import com.echatman.nextbus.response.predictions.PredictionsResponse;
import com.echatman.nextbus.response.routeconfig.RouteConfig;
import com.echatman.nextbus.response.routeconfig.RouteConfigResponse;
import com.echatman.nextbus.response.routeconfig.StopConfig;
import com.echatman.nextbus.response.routelist.Route;
import com.echatman.nextbus.response.routelist.RouteListResponse;
import com.echatman.nextbus.response.schedule.ScheduleResponse;
import com.echatman.nextbus.test.TestUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.fail;

/**
 * @author echatman
 */
@RunWith(Enclosed.class)
public class NextBusServiceMarshallingTest {

    // Inner classes check this and stop running after a failure.
    static boolean failureOccurred = false;

    static NextBusService nextBusService;

    // agency tags
    private static List<String> agencies;
    // agency tag -> pair of route tags
    private static Map<String, Pair<String, String>> routes;
    // (agency tag, route tag) -> pair of stop tags
    private static Map<Pair<String,String>, Pair<String,String>> stops;

    // There are a lot of tests that get run once for every agency.
    // Higher number = much more thorough testing, but a lot slower.
    private final static int MAX_AGENCIES = 100;

    public static List<String> getAgencies() throws Exception {
        if (agencies == null) {
            AgencyListResponse agenciesResponse = new NextBusService().agencyList();
            TestUtils.checkForErrors(agenciesResponse);
            agencies = agenciesResponse.getAgencies().stream()
                    .map(Agency::getTag)
                    .limit(MAX_AGENCIES)
                    .collect(toList());
            System.out.println("Found " + agencies.size() + " agencies");
        }
        return agencies;
    }

    public static Map<String, Pair<String, String>> getRoutes() throws Exception {
        if (routes == null) {
            routes = new HashMap<>();
            for (String agencyTag : getAgencies()) {
                RouteListResponse routeList = new NextBusService().routeList(agencyTag);
                TestUtils.checkForErrors(routeList);
                List<String> routeTags = routeList.getRoutes().stream()
                        .map(Route::getTag)
                        .collect(toList());
                if (routeTags.isEmpty()) {
                    System.err.println("Warning: No routes found for agency " + agencyTag);
                } else {
                    // Use the first two route tags for each agency (TODO: randomize this?)
                    String routeTag1 = routeTags.get(0);
                    String routeTag2 = routeTags.size() > 1 ? routeTags.get(1) : null;
                    routes.put(agencyTag, Pair.of(routeTag1, routeTag2));
                }
            }
        }
        return routes;
    }

    public static Map<Pair<String,String>, Pair<String,String>> getStops() throws Exception {
        if (stops == null) {
            stops = new HashMap<>();
            for (Map.Entry<String, Pair<String, String>> entry : getRoutes().entrySet()) {
                String agencyTag = entry.getKey();
                String routeTag = entry.getValue().getLeft();
                RouteConfigRequest routeConfigRequest = RouteConfigRequest.forRoute(agencyTag, routeTag);
                RouteConfigResponse routeConfigResponse = new NextBusService().routeConfig(routeConfigRequest);
                TestUtils.checkForErrors(routeConfigResponse);
                if (routeConfigResponse.getRoutes().isEmpty()) {
                    fail("No config found for route " + routeConfigResponse.getUrl());
                }
                RouteConfig routeConfig = routeConfigResponse.getRoutes().get(0);
                List<String> stopTags = routeConfig.getStops().stream()
                        .map(StopConfig::getTag).collect(toList());
                if (stopTags.isEmpty()) {
                    System.err.println("Warning: no stops found for agency " + agencyTag + ", route " + routeTag);
                } else {
                    // Use the first two route stops for each route (TODO: randomize this?)
                    String stopTag1 = stopTags.get(0);
                    String stopTag2 = stopTags.size() > 1 ? stopTags.get(1) : null;
                    stops.put(Pair.of(agencyTag, routeTag), Pair.of(stopTag1, stopTag2));
                }
            }
            System.out.println("Found " + stops.size() + " stop configurations");
        }
        return stops;
    }

    @BeforeClass
    public static void setUp() {
        nextBusService = new NextBusService();
    }

    // Tests grouped by the necessary parameters (no need to run the agency list once per agency, for example)
    public static class NoParameter {
        @Test
        public void testAgencyListMarshalling() throws Exception {
            TestUtils.testMarshallingURI(AgencyListResponse.class,
                    nextBusService.buildAgencyListURI());
        }
    }

    @RunWith(Parameterized.class)
    public static class AgencyParameter {

        @Parameterized.Parameters(name="{index}: agency {0}")
        public static Collection<Object[]> data() throws Exception {
            return getAgencies().stream().map(tag -> new String[]{tag}).collect(toList());
        }

        @Parameterized.Parameter
        public String agencyTag;

        // Stop running tests after a failure occurs
        @Rule
        public TestRule watcher = new TestWatcher() {
            @Override
            protected void failed(Throwable e, Description description) {
                failureOccurred = true;
            }
        };

        @Before
        public void before() {
            Assume.assumeFalse(failureOccurred);
        }

        @Test
        public void testRouteListMarshalling() throws Exception {
            TestUtils.testMarshallingURI(RouteListResponse.class,
                    nextBusService.buildRouteListURI(agencyTag));
        }

        @Test
        public void testRouteConfigMarshalling_allRoutes() throws Exception {
            TestUtils.testMarshallingURI(RouteConfigResponse.class,
                    nextBusService.buildRouteConfigURI(RouteConfigRequest.forAllRoutes(agencyTag)));
        }
    }

    @RunWith(Parameterized.class)
    public static class RouteParameter {

        @Parameterized.Parameters(name="{index}: agency {0}, routes {1} {2}")
        public static Collection<Object[]> data() throws Exception {
            List<Object[]> data = new ArrayList<>();
            for (Map.Entry<String, Pair<String, String>> entry : getRoutes().entrySet()) {
                String agencyTag = entry.getKey();
                String routeTag1 = entry.getValue().getLeft();
                String routeTag2 = entry.getValue().getRight();
                data.add(new String[]{agencyTag, routeTag1, routeTag2});
            }
            return data;
        }

        @Parameterized.Parameter(0)
        public String agencyTag;
        @Parameterized.Parameter(1)
        public String routeTag1;
        @Parameterized.Parameter(2)
        public String routeTag2;

        // Stop running tests after a failure occurs
        @Rule
        public TestRule watcher = new TestWatcher() {
            @Override
            protected void failed(Throwable e, Description description) {
                failureOccurred = true;
            }
        };

        @Before
        public void before() {
            Assume.assumeFalse(failureOccurred);
        }

        @Test
        public void testRouteConfigMarshalling_singleRoute() throws Exception {
            TestUtils.testMarshallingURI(RouteConfigResponse.class,
                    nextBusService.buildRouteConfigURI(RouteConfigRequest.forRoute(agencyTag, routeTag1)));
        }

        @Test
        public void testVehicleLocationsMarshalling() throws Exception {
            TestUtils.testMarshallingURI(VehicleLocationsResponse.class,
                    nextBusService.buildVehicleLocationsURI(agencyTag, routeTag1, 0));
        }

        @Test
        public void testMessagesMarshalling() throws Exception {
            TestUtils.testMarshallingURI(MessagesResponse.class,
                    nextBusService.buildMessagesURI(agencyTag, routeTag1));
        }

        @Test
        public void testMessagesMarshalling_multipleRoutes() throws Exception {
            // Skip this test if the agency only has one route
            Assume.assumeNotNull(routeTag2);
            TestUtils.testMarshallingURI(MessagesResponse.class,
                    nextBusService.buildMessagesURI(agencyTag, routeTag1, routeTag2));
        }

        @Test
        public void testScheduleMarshalling() throws Exception {
            TestUtils.testMarshallingURI(ScheduleResponse.class,
                    nextBusService.buildScheduleURI(agencyTag, routeTag1));
        }
    }

    @RunWith(Parameterized.class)
    public static class StopParameter {

        @Parameterized.Parameters(name="{index}: agency {0}, route {1}, stops {3} {4}")
        public static Collection<Object[]> data() throws Exception {
            List<Object[]> data = new ArrayList<>();
            for (Map.Entry<Pair<String, String>, Pair<String,String>> entry : getStops().entrySet()) {
                String agencyTag = entry.getKey().getLeft();
                String routeTag = entry.getKey().getRight();
                String stopTag1 = entry.getValue().getLeft();
                String stopTag2 = entry.getValue().getRight();
                data.add(new String[]{agencyTag, routeTag, stopTag1, stopTag2});
            }
            return data;
        }

        @Parameterized.Parameter(0)
        public String agencyTag;
        @Parameterized.Parameter(1)
        public String routeTag;
        @Parameterized.Parameter(2)
        public String stopTag1;
        @Parameterized.Parameter(3)
        public String stopTag2;

        // Stop running tests after a failure occurs
        @Rule
        public TestRule watcher = new TestWatcher() {
            @Override
            protected void failed(Throwable e, Description description) {
                failureOccurred = true;
            }
        };

        @Before
        public void before() {
            Assume.assumeFalse(failureOccurred);
        }

        @Test
        public void testPredictionsMarshalling() throws Exception {
            TestUtils.testMarshallingURI(PredictionsResponse.class,
                    nextBusService.buildPredictionsURI(
                            PredictionsRequest.forRouteTagAndStopTag(agencyTag, routeTag, stopTag1)));
        }

        @Test
        public void testPredictionsForMultiStopsMarshalling() throws Exception {
            // Skip this test if we couldn't find two stop tags for some reason
            Assume.assumeNotNull(stopTag2);
            TestUtils.testMarshallingURI(PredictionsForMultiStopsResponse.class,
                    nextBusService.buildPredictionsForMultiStopsURI(new PredictionsForMultiStopsRequest(agencyTag,
                            routeTag, stopTag1, routeTag, stopTag2)));
        }
    }
}
