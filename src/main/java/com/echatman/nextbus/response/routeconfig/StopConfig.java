package com.echatman.nextbus.response.routeconfig;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 * @author echatman
 */
@XmlType
public class StopConfig {

    private BigDecimal lat;
    private BigDecimal lon;
    private String shortTitle;
    private String stopId;
    private String tag;
    private String title;

    /**
     * lat/lon - specify the location of the stop.
     */
    @XmlAttribute
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * lat/lon - specify the location of the stop.
     */
    @XmlAttribute
    public BigDecimal getLon() {
        return lon;
    }

    /**
     * some transit agencies define short version of the title that are
     * useful for applications where screen real estate is limited. This
     * element is only provided when a separate short title exists.
     */
    @XmlAttribute
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * an optional numeric ID to identify a stop. Useful for telephone
     * or SMS systems so that a user can simply enter the numeric ID to
     * identify a stop instead of having to select a route, direction,
     * and stop. Not all transit agencies have numeric IDs to identify a
     * stop so this element will not always be available.
     *
     * @see #getStopIdNumeric()
     */
    @XmlAttribute
    public String getStopId() {
        return stopId;
    }

    /**
     * Convert the stop id to a Long
     *
     * @return
     */
    @XmlTransient
    public Long getStopIdNumeric() {
        return stopId == null ? null : Long.valueOf(stopId);
    }

    /**
     * unique alphanumeric identifier for stop, such as “cp_1321”. Even
     * if the stop tags appear to usually be numeric they can sometimes
     * contain alphabetical characters. Therefore the stop tags cannot
     * be used as a number for telephone systems and other such
     * applications . For larger agencies such as Toronto TTC s uffixes
     * "_IB" and “_OB" are included at the end of the stop tag for the
     * rare situations when an agency has defined only a single stop for
     * both directions and the stop is not an arrival at the end of the
     * route (in cases of arrivals “_ar” is used). This means that the
     * stop tag might not always correspond to GTFS or other
     * configuration data. These suffixes allow duplicate stops to have
     * the identical stopID as the original stop while preserving both
     * unique stops in the system. "_IB" represents a duplicated inbound
     * stop, and "_OB" represents a duplicated outbound stop.
     */
    @XmlAttribute
    @XmlID
    public String getTag() {
        return tag;
    }

    /**
     * the name of the stop to displayed in a User Interface, such as “5
     * th St & Main, City Hall”.
     */
    @XmlAttribute
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    private void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @SuppressWarnings("unused")
    private void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    @SuppressWarnings("unused")
    private void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    @SuppressWarnings("unused")
    private void setStopId(String stopId) {
        this.stopId = stopId;
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
