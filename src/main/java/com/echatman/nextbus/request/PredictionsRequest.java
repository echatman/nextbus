// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.request;

/**
 * 
 * @author echatman
 */
public class PredictionsRequest {

    public static PredictionsRequest forStopId(String agencyTag, String stopId) {
        return new PredictionsRequest().setAgencyTag(agencyTag).setStopId(stopId);
    }

    public static PredictionsRequest forStopTagAndRouteTag(String agencyTag, String stopTag, String routeTag) {
        return new PredictionsRequest().setAgencyTag(agencyTag).setStopTag(stopTag).setRouteTag(routeTag);
    }

    private String agencyTag;
    private String routeTag;
    private String stopId;
    private String stopTag;
    private boolean useShortTitles;

    private PredictionsRequest() {
    }

    public String getAgencyTag() {
        return agencyTag;
    }

    public String getRouteTag() {
        return routeTag;
    }

    public String getStopId() {
        return stopId;
    }

    public String getStopTag() {
        return stopTag;
    }

    public boolean isUseShortTitles() {
        return useShortTitles;
    }

    public PredictionsRequest setRouteTag(String routeTag) {
        this.routeTag = routeTag;
        return this;
    }

    public PredictionsRequest setUseShortTitles(boolean useShortTitles) {
        this.useShortTitles = useShortTitles;
        return this;
    }

    private PredictionsRequest setAgencyTag(String agencyTag) {
        this.agencyTag = agencyTag;
        return this;
    }

    private PredictionsRequest setStopId(String stopId) {
        this.stopId = stopId;
        return this;
    }

    private PredictionsRequest setStopTag(String stopTag) {
        this.stopTag = stopTag;
        return this;
    }
}
