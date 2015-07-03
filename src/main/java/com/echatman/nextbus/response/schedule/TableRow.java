package com.echatman.nextbus.response.schedule;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author echatman
 */
@XmlType
public class TableRow {

    private String blockID;
    private List<ScheduledStop> stops = new ArrayList<>();

    @XmlAttribute
    public String getBlockID() {
        return blockID;
    }

    @XmlElement(name = "stop")
    public List<ScheduledStop> getStops() {
        return stops;
    }

    @SuppressWarnings("unused")
    private void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    @SuppressWarnings("unused")
    private void setStops(List<ScheduledStop> stops) {
        this.stops = stops;
    }
}
