// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus;

import org.junit.Before;
import org.junit.Test;

import com.echatman.nextbus.domain.AgencyList;
import com.echatman.nextbus.domain.RouteConfig;
import com.echatman.nextbus.domain.RouteList;
import com.echatman.nextbus.request.RouteConfigRequest;
import com.echatman.nextbus.test.TestUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * 
 * @author echatman
 */
public class NextBusServiceTest {

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
        AgencyList.Agency agency = agencyList.getAgencies().get(0);
        assertNotNull("Agency list should not contain nulls", agency);
        assertNotNull("Agency title should not be null", agency.getTitle());
        // Compare XML
        TestUtils.assertXmlMatches(nextBusService.buildAgencyListURI(), AgencyList.class, agencyList);
    }

    @Test
    public void testRouteList() throws Exception {
        RouteList routeList = nextBusService.routeList(agencyTag);
        // Basic validation of result
        assertNotNull("Route list should not return null", routeList);
        assertNull("Route list returned error", routeList.getError());
        assertNotNull("List of routes should not be null", routeList.getRoutes());
        assertFalse("List of routes should not be empty", routeList.getRoutes().isEmpty());
        RouteList.Route route = routeList.getRoutes().get(0);
        assertNotNull("Route list should not contain nulls", route);
        assertNotNull("Route title should not be null", route.getTitle());
        // Compare XML
        TestUtils.assertXmlMatches(nextBusService.buildRouteListURI(agencyTag), RouteList.class, routeList);
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
        RouteConfig.Route route = routeConfig.getRoutes().get(0);
        assertNotNull("Route config should not contain nulls", route);
        assertNotNull("Route title should not be null", route.getTitle());
        // Compare XML
        TestUtils.assertXmlMatches(nextBusService.buildRouteConfigURI(request), RouteConfig.class, routeConfig);
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
        RouteConfig.Route route = routeConfig.getRoutes().get(0);
        assertNotNull("Route config should not contain nulls", route);
        assertNotNull("Route title should not be null", route.getTitle());
        // Compare XML
        TestUtils.assertXmlMatches(nextBusService.buildRouteConfigURI(request), RouteConfig.class, routeConfig);
    }
}
