package com.echatman.nextbus.response.routeconfig;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author echatman
 */
@XmlType
public class Path {

    private List<Tag> tags = new ArrayList<>();
    private List<Point> points = new ArrayList<>();

    @XmlElement(name = "point")
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Not in the documentation, but observed in the responses from "mit" agency
     */
    @XmlElement(name = "tag")
    public List<Tag> getTags() {
        return tags;
    }

    @SuppressWarnings("unused")
    private void setPoints(List<Point> points) {
        this.points = points;
    }

    @SuppressWarnings("unused")
    private void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
