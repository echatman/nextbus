// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.request;

import com.echatman.nextbus.NextBusService;

/**
 * 
 * @author echatman
 */
public class RouteConfigRequest {

    /**
     * Use this to request information about all routes for an agency. The tag
     * for the agency as obtained from the {@link NextBusService#agencyList()}
     * command should be used. Data for all routes for the agency is returned.
     * Due to the large size of the resulting XML the routeConfig command is
     * limited to providing data for only up to 100 routes per request. If an
     * agency has more than 100 routes then multiple {@link #forRoute(String, String)}
     * requests would need to be used to read data for all routes.
     * 
     * @param agencyTag
     *            The tag for the agency as obtained from the
     *            {@link NextBusService#agencyList()} command
     * @return RouteConfigRequest
     */
    public static RouteConfigRequest forAllRoutes(String agencyTag) {
        return new RouteConfigRequest().setAgencyTag(agencyTag);
    }

    /**
     * Use this to request information about a particular route for an agency.
     * The agency is specified by the "a" parameter in the query string. The tag
     * for the agency as obtained from the {@link NextBusService#agencyList()}
     * command should be used. The route is specified by the "r" parameter. The
     * tag for the route is obtained using the
     * {@link NextBusService#routeList(String)} command.
     * 
     * @param agencyTag
     *            The tag for the agency as obtained from the
     *            {@link NextBusService#agencyList()} command
     * @param routeTag
     *            The tag for the route as obtained from the
     *            {@link NextBusService#routeList(String)} command.
     * @return RouteConfigRequest
     */
    public static RouteConfigRequest forRoute(String agencyTag, String routeTag) {
        return new RouteConfigRequest().setAgencyTag(agencyTag).setRouteTag(routeTag);
    }

    private String agencyTag;
    private String routeTag;
    private boolean terse;
    private boolean verbose;

    private RouteConfigRequest() {
    }

    public String getAgencyTag() {
        return agencyTag;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public boolean isTerse() {
        return terse;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public RouteConfigRequest setTerse(boolean terse) {
        this.terse = terse;
        return this;
    }

    public RouteConfigRequest setVerbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    private RouteConfigRequest setAgencyTag(String agencyTag) {
        this.agencyTag = agencyTag;
        return this;
    }

    private RouteConfigRequest setRouteTag(String routeTag) {
        this.routeTag = routeTag;
        return this;
    }
}
