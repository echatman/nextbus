package com.echatman.nextbus.response.routeconfig;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author echatman
 */
@XmlType
public class RouteConfig {

    private String tag;
    private String title;
    private String shortTitle;
    private String color;
    private String oppositeColor;
    private BigDecimal latMin;
    private BigDecimal latMax;
    private BigDecimal lonMin;
    private BigDecimal lonMax;
    private List<StopConfig> stops = new ArrayList<>();
    private List<Direction> directions = new ArrayList<>();
    private List<Path> paths = new ArrayList<>();

    /**
     * the color in hexadecimal format associated with the route. Useful for
     * User Interfaces such as maps.
     */
    @XmlAttribute
    public String getColor() {
        return color;
    }

    /**
     * latMin , latMax , lonMin , lonMax – specifies the extent of the
     * route.
     */
    @XmlAttribute
    public BigDecimal getLatMax() {
        return latMax;
    }

    /**
     * latMin , latMax , lonMin , lonMax – specifies the extent of the
     * route.
     */
    @XmlAttribute
    public BigDecimal getLatMin() {
        return latMin;
    }

    /**
     * latMin , latMax , lonMin , lonMax – specifies the extent of the
     * route.
     */
    @XmlAttribute
    public BigDecimal getLonMax() {
        return lonMax;
    }

    /**
     * latMin , latMax , lonMin , lonMax – specifies the extent of the
     * route.
     */
    @XmlAttribute
    public BigDecimal getLonMin() {
        return lonMin;
    }

    /**
     * the color that most contrasts with the route color. Specified in
     * hexadecimal format. Useful for User Interfaces such as maps. Will be
     * either black or white.
     */
    @XmlAttribute
    public String getOppositeColor() {
        return oppositeColor;
    }

    /**
     * for some transit agencies shorter titles are provided that can be
     * useful for User Interfaces where there is not much screen real
     * estate, such as on smartphones. This element is only provided where a
     * short title is actually available. If a short title is not available
     * then the regular title element should be used.
     */
    @XmlAttribute
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * unique alphanumeric identifier for route, such as “N”.
     */
    @XmlAttribute
    public String getTag() {
        return tag;
    }

    /**
     * the name of the route to be displayed in a User Interface, such as “N
     * - Judah”.
     */
    @XmlAttribute
    public String getTitle() {
        return title;
    }

    @XmlElement(name = "stop")
    public List<StopConfig> getStops() {
        return stops;
    }

    @XmlElement(name = "direction")
    public List<Direction> getDirections() {
        return directions;
    }

    /**
     * The paths are simply lists of coordinates that can be used to draw a
     * route on a map. The path data can be voluminous. If you do not need
     * the path data you should add “&terse” to the routeConfig URL and the
     * volume of returned data will be cut approximately in half. This is
     * especially useful for mobile apps where you want to transfer as
     * little data as possible. Due to the nature of the configuration there
     * can be many separate paths, some of them overlapping. A map client
     * should simply draw all of the paths. The paths are not necessa rily
     * in any kind of order so you should only connect the points within a
     * path. You should not connect the points between two separate paths
     * though.
     */
    @XmlElement(name = "path")
    public List<Path> getPaths() {
        return paths;
    }

    @SuppressWarnings("unused")
    private void setColor(String color) {
        this.color = color;
    }

    @SuppressWarnings("unused")
    private void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    @SuppressWarnings("unused")
    private void setLatMax(BigDecimal latMax) {
        this.latMax = latMax;
    }

    @SuppressWarnings("unused")
    private void setLatMin(BigDecimal latMin) {
        this.latMin = latMin;
    }

    @SuppressWarnings("unused")
    private void setLonMax(BigDecimal lonMax) {
        this.lonMax = lonMax;
    }

    @SuppressWarnings("unused")
    private void setLonMin(BigDecimal lonMin) {
        this.lonMin = lonMin;
    }

    @SuppressWarnings("unused")
    private void setOppositeColor(String oppositeColor) {
        this.oppositeColor = oppositeColor;
    }

    @SuppressWarnings("unused")
    private void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    @SuppressWarnings("unused")
    private void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    @SuppressWarnings("unused")
    private void setStops(List<StopConfig> stops) {
        this.stops = stops;
    }

    @SuppressWarnings("unused")
    private void setTag(String tag) {
        this.tag = tag;
    }

    @SuppressWarnings("unused")
    private void setTitle(String title) {
        this.title = title;
    }

}
