// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus;

import com.echatman.nextbus.response.agencylist.Agency;
import com.echatman.nextbus.response.agencylist.AgencyListResponse;
import com.echatman.nextbus.response.routeconfig.RouteConfig;
import com.echatman.nextbus.response.routeconfig.RouteConfigResponse;
import com.echatman.nextbus.response.routelist.Route;
import com.echatman.nextbus.response.routelist.RouteListResponse;
import com.echatman.nextbus.request.RouteConfigRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

/**
 * 
 * @author echatman
 */
public class NextBusServiceTest {

    // These need to actually exist
    String agencyTag = "sf-muni";
    String routeTag = "N";
    String routeTag2 = "KT";
    NextBusService nextBusService;

    @Before
    public void setUp() {
        nextBusService = new NextBusService();
    }

    @Test
    public void testAgencyList() throws Exception {
        AgencyListResponse agencyList = nextBusService.agencyList();
        // Basic validation of result
        assertNotNull("Agency list should not return null", agencyList);
        assertNull("Agency list returned error", agencyList.getError());
        assertNotNull("List of agencies should not be null", agencyList.getAgencies());
        assertFalse("List of agencies should not be empty", agencyList.getAgencies().isEmpty());
        Collection<String> agencyTags = new HashSet<String>(agencyList.getAgencies().size());
        for (Agency agency : agencyList.getAgencies()) {
            assertNotNull("Agency list should not contain nulls", agency);
            assertNotNull("Agency title should not be null", agency.getTitle());
            agencyTags.add(agency.getTag());
        }
        assertThat("Agency " + agencyTag + " should appear in agency list", agencyTags, hasItem(agencyTag));
    }

    @Test
    public void testRouteList() throws Exception {
        RouteListResponse routeList = nextBusService.routeList(agencyTag);
        // Basic validation of result
        assertNotNull("Route list should not return null", routeList);
        assertNull("Route list returned error", routeList.getError());
        assertNotNull("List of routes should not be null", routeList.getRoutes());
        assertFalse("List of routes should not be empty", routeList.getRoutes().isEmpty());
        Collection<String> routeTags = new HashSet<String>(routeList.getRoutes().size());
        for (Route route : routeList.getRoutes()) {
            assertNotNull("Route list should not contain nulls", route);
            assertNotNull("Route title should not be null", route.getTitle());
            routeTags.add(route.getTag());
        }
        assertThat("Route " + routeTag + " should appear in route list for agency " + agencyTag, routeTags,
                hasItem(routeTag));
    }

    @Test
    public void testRouteConfig_allRoutes() throws Exception {
        RouteConfigRequest request = RouteConfigRequest.forAllRoutes(agencyTag);
        RouteConfigResponse routeConfig = nextBusService.routeConfig(request);
        // Basic validation of result
        assertNotNull("Route config should not return null", routeConfig);
        assertNull("Route config returned error", routeConfig.getError());
        assertNotNull("Config of routes should not be null", routeConfig.getRoutes());
        assertFalse("Config of routes should not be empty", routeConfig.getRoutes().isEmpty());
        Collection<String> routeTags = new HashSet<String>(routeConfig.getRoutes().size());
        for (RouteConfig route : routeConfig.getRoutes()) {
            assertNotNull("Route config should not contain nulls", route);
            assertNotNull("Route title should not be null", route.getTitle());
            routeTags.add(route.getTag());
        }
        assertThat("Route " + routeTag + " should appear in route config list for agency " + agencyTag, routeTags,
                hasItem(routeTag));
    }

    @Test
    public void testRouteConfig_singleRoute() throws Exception {
        RouteConfigRequest request = RouteConfigRequest.forRoute(agencyTag, routeTag);
        RouteConfigResponse routeConfig = nextBusService.routeConfig(request);
        // Basic validation of result
        assertNotNull("Route config should not return null", routeConfig);
        assertNull("Route config returned error", routeConfig.getError());
        assertNotNull("Config of routes should not be null", routeConfig.getRoutes());
        assertFalse("Config of routes should not be empty", routeConfig.getRoutes().isEmpty());
        Collection<String> routeTags = new HashSet<String>(routeConfig.getRoutes().size());
        for (RouteConfig route : routeConfig.getRoutes()) {
            assertNotNull("Route config should not contain nulls", route);
            assertNotNull("Route title should not be null", route.getTitle());
            routeTags.add(route.getTag());
        }
        assertThat("Route " + routeTag + " should appear in route config list for agency " + agencyTag, routeTags,
                hasItem(routeTag));
    }
}
