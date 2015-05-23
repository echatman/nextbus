package com.echatman.nextbus.request;

import java.util.ArrayList;
import java.util.List;

/**
 * @author echatman
 */
public class PredictionsForMultiStopsRequest {
    private String agencyTag;
    private List<String> stops;
    private boolean useShortTitles;

    public PredictionsForMultiStopsRequest(String agencyTag) {
        this.agencyTag = agencyTag;
        this.stops = new ArrayList<>();
        this.useShortTitles = false;
    }

    public PredictionsForMultiStopsRequest(String agencyTag, String routeTag1, String stopTag1,
                                           String routeTag2, String stopTag2) {
        this(agencyTag);
        addStop(routeTag1, stopTag1);
        addStop(routeTag2, stopTag2);
    }

    public void addStop(String routeTag, String stopTag) {
        stops.add(routeTag + '|' + stopTag);
    }

    public void setUseShortTitles(boolean useShortTitles) {
        this.useShortTitles = useShortTitles;
    }

    public String getAgencyTag() {
        return agencyTag;
    }

    public List<String> getStops() {
        return stops;
    }

    public boolean isUseShortTitles() {
        return useShortTitles;
    }
}
