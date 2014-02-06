// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.echatman.nextbus.domain.AgencyList;
import com.echatman.nextbus.domain.RouteConfig;
import com.echatman.nextbus.domain.RouteList;
import com.echatman.nextbus.request.RouteConfigRequest;
import com.echatman.nextbus.test.TestUtils;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * 
 * @author echatman
 */
public class NextBusServiceTest {

    // These need to actually exist
    String agencyTag = "sf-muni";
    String routeTag = "N";
    NextBusService nextBusService;

    @Before
    public void setUp() {
        nextBusService = new NextBusService();
    }

    @Test
    public void testAgencyList() throws Exception {
        AgencyList agencyList = nextBusService.agencyList();
        // Basic validation of result
        assertNotNull("Agency list should not return null", agencyList);
        assertNull("Agency list returned error", agencyList.getError());
        assertNotNull("List of agencies should not be null", agencyList.getAgencies());
        assertFalse("List of agencies should not be empty", agencyList.getAgencies().isEmpty());
        Collection<String> agencyTags = new HashSet<String>(agencyList.getAgencies().size());
        for (AgencyList.Agency agency : agencyList.getAgencies()) {
            assertNotNull("Agency list should not contain nulls", agency);
            assertNotNull("Agency title should not be null", agency.getTitle());
            agencyTags.add(agency.getTag());
        }
        assertThat("Agency " + agencyTag + " should appear in agency list", agencyTags, hasItem(agencyTag));
    }

    @Test
    public void testAgencyListMarshalling() throws Exception {
        TestUtils.testMarshalling(AgencyList.class,
                TestUtils.getURIContentsAsString(nextBusService.buildAgencyListURI()));
    }

    @Test
    public void testRouteList() throws Exception {
        RouteList routeList = nextBusService.routeList(agencyTag);
        // Basic validation of result
        assertNotNull("Route list should not return null", routeList);
        assertNull("Route list returned error", routeList.getError());
        assertNotNull("List of routes should not be null", routeList.getRoutes());
        assertFalse("List of routes should not be empty", routeList.getRoutes().isEmpty());
        Collection<String> routeTags = new HashSet<String>(routeList.getRoutes().size());
        for (RouteList.Route route : routeList.getRoutes()) {
            assertNotNull("Route list should not contain nulls", route);
            assertNotNull("Route title should not be null", route.getTitle());
            routeTags.add(route.getTag());
        }
        assertThat("Route " + routeTag + " should appear in route list for agency " + agencyTag, routeTags,
                hasItem(routeTag));
    }

    @Test
    public void testRouteListMarshalling() throws Exception {
        TestUtils.testMarshalling(RouteList.class,
                TestUtils.getURIContentsAsString(nextBusService.buildRouteListURI(agencyTag)));
    }

    @Test
    public void testRouteConfig_allRoutes() throws Exception {
        RouteConfigRequest request = RouteConfigRequest.forAllRoutes(agencyTag);
        RouteConfig routeConfig = nextBusService.routeConfig(request);
        // Basic validation of result
        assertNotNull("Route config should not return null", routeConfig);
        assertNull("Route config returned error", routeConfig.getError());
        assertNotNull("Config of routes should not be null", routeConfig.getRoutes());
        assertFalse("Config of routes should not be empty", routeConfig.getRoutes().isEmpty());
        Collection<String> routeTags = new HashSet<String>(routeConfig.getRoutes().size());
        for (RouteConfig.Route route : routeConfig.getRoutes()) {
            assertNotNull("Route config should not contain nulls", route);
            assertNotNull("Route title should not be null", route.getTitle());
            routeTags.add(route.getTag());
        }
        assertThat("Route " + routeTag + " should appear in route config list for agency " + agencyTag, routeTags,
                hasItem(routeTag));
    }

    @Test
    public void testRouteConfigMarshalling_allRoutes() throws Exception {
        TestUtils.testMarshalling(RouteConfig.class, TestUtils.getURIContentsAsString(nextBusService
                .buildRouteConfigURI(RouteConfigRequest.forAllRoutes(agencyTag))));
    }

    @Test
    public void testRouteConfig_singleRoute() throws Exception {
        RouteConfigRequest request = RouteConfigRequest.forRoute(agencyTag, routeTag);
        RouteConfig routeConfig = nextBusService.routeConfig(request);
        // Basic validation of result
        assertNotNull("Route config should not return null", routeConfig);
        assertNull("Route config returned error", routeConfig.getError());
        assertNotNull("Config of routes should not be null", routeConfig.getRoutes());
        assertFalse("Config of routes should not be empty", routeConfig.getRoutes().isEmpty());
        Collection<String> routeTags = new HashSet<String>(routeConfig.getRoutes().size());
        for (RouteConfig.Route route : routeConfig.getRoutes()) {
            assertNotNull("Route config should not contain nulls", route);
            assertNotNull("Route title should not be null", route.getTitle());
            routeTags.add(route.getTag());
        }
        assertThat("Route " + routeTag + " should appear in route config list for agency " + agencyTag, routeTags,
                hasItem(routeTag));
    }

    @Test
    public void testRouteConfigMarshalling_singleRoute() throws Exception {
        TestUtils.testMarshalling(RouteConfig.class, TestUtils.getURIContentsAsString(nextBusService
                .buildRouteConfigURI(RouteConfigRequest.forRoute(agencyTag, routeTag))));
    }
}
