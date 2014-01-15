// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author echatman
 */
@XmlType(name = "Stop")
public class Stop {
    /**
     * unique alphanumeric identifier for stop, such as “cp_1321”. Even if the
     * stop tags appear to usually be numeric they can sometimes contain
     * alphabetical characters. Therefore the stop tags cannot be used as a
     * number for telephone systems and other such applications . For larger
     * agencies such as Toronto TTC s uffixes "_IB" and “_OB" are included at
     * the end of the stop tag for the rare situations when an agency has
     * defined only a single stop for both directions and the stop is not an
     * arrival at the end of the route (in cases of arrivals “_ar” is used).
     * This means that the stop tag might not always correspond to GTFS or other
     * configuration data. These suffixes allow duplicate stops to have the
     * identical stopID as the original stop while preserving both unique stops
     * in the system. "_IB" represents a duplicated inbound stop, and "_OB"
     * represents a duplicated outbound stop.
     */
    @XmlAttribute
    @XmlID
    String tag;
    /**
     * the name of the stop to displayed in a User Interface, such as “5 th St &
     * Main, City Hall”.
     */
    @XmlAttribute
    String title;
    /**
     * some transit agencies define short version of the title that are useful
     * for applications where screen real estate is limited. This element is
     * only provided when a separate short title exists.
     */
    @XmlAttribute
    String shortTitle;
    /**
     * lat/lon - specify the location of the stop.
     */
    @XmlAttribute
    BigDecimal lat;
    /**
     * lat/lon - specify the location of the stop.
     */
    @XmlAttribute
    BigDecimal lon;
    /**
     * an optional numeric ID to identify a stop. Useful for telephone or SMS
     * systems so that a user can simply enter the numeric ID to identify a stop
     * instead of having to select a route, direction, and stop. Not all transit
     * agencies have numeric IDs to identify a stop so this element will not
     * always be available.
     */
    @XmlAttribute
    Long stopId;
}
