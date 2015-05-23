package com.echatman.nextbus.response.routeconfig;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 * @author echatman
 */
@XmlType
public class Point {

    private BigDecimal lat;
    private BigDecimal lon;

    @XmlAttribute
    public BigDecimal getLat() {
        return lat;
    }

    @XmlAttribute
    public BigDecimal getLon() {
        return lon;
    }

    @SuppressWarnings("unused")
    private void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @SuppressWarnings("unused")
    private void setLon(BigDecimal lon) {
        this.lon = lon;
    }
}
