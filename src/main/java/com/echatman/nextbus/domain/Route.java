// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author echatman
 */
@XmlType(name = "Route")
public class Route {
    /**
     * unique alphanumeric identifier for route, such as “N”.
     */
    @XmlAttribute
    String tag;
    /**
     * the name of the route to be displayed in a User Interface, such as “N -
     * Judah”.
     */
    @XmlAttribute
    String title;
    /**
     * for some transit agencies shorter titles are provided that can be useful
     * for User Interfaces where there is not much screen real estate, such as
     * on smartphones. This element is only provided where a short title is
     * actually available. If a short title is not available then the regular
     * title element should be used.
     */
    @XmlAttribute
    String shortTitle;
    /**
     * the color in hexadecimal format associated with the route. Useful for
     * User Interfaces such as maps.
     */
    @XmlAttribute
    String color;
    /**
     * the color that most contrasts with the route color. Specified in
     * hexadecimal format. Useful for User Interfaces such as maps. Will be
     * either black or white.
     */
    @XmlAttribute
    String oppositeColor;
    /**
     * latMin , latMax , lonMin , lonMax – specifies the extent of the route.
     */
    @XmlAttribute
    BigDecimal latMin;
    /**
     * latMin , latMax , lonMin , lonMax – specifies the extent of the route.
     */
    @XmlAttribute
    BigDecimal latMax;
    /**
     * latMin , latMax , lonMin , lonMax – specifies the extent of the route.
     */
    @XmlAttribute
    BigDecimal lonMin;
    /**
     * latMin , latMax , lonMin , lonMax – specifies the extent of the route.
     */
    @XmlAttribute
    BigDecimal lonMax;
    @XmlElement(name = "stop")
    List<Stop> stops;
    @XmlElement(name = "direction")
    List<Direction> directions;
    /**
     * The paths are simply lists of coordinates that can be used to draw a
     * route on a map. The path data can be voluminous. If you do not need the
     * path data you should add “&terse” to the routeConfig URL and the volume
     * of returned data will be cut approximately in half. This is especially
     * useful for mobile apps where you want to transfer as little data as
     * possible. Due to the nature of the configuration there can be many
     * separate paths, some of them overlapping. A map client should simply draw
     * all of the paths. The paths are not necessa rily in any kind of order so
     * you should only connect the points within a path. You should not connect
     * the points between two separate paths though.
     */
    @XmlElement(name = "path")
    List<Path> paths;
}
