package com.echatman.nextbus.response.messages;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author echatman
 */
@XmlType
public class RouteConfiguredForMessage {

    private List<Stop> stops;
    private String tag;

    @XmlElement(name = "stop")
    public List<Stop> getStops() {
        return stops;
    }

    @XmlAttribute(name = "tag")
    public String getTag() {
        return tag;
    }

    @SuppressWarnings("unused")
    private void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    @SuppressWarnings("unused")
    private void setTag(String tag) {
        this.tag = tag;
    }

}
