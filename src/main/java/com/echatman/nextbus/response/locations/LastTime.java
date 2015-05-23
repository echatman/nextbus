package com.echatman.nextbus.response.locations;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author echatman
 */
@XmlType
public class LastTime {

    private Long time;

    /**
     * (msec since 1970 epoch time) – Specifies time of the last update for
     * the vehicle location data returned.
     */
    @XmlAttribute
    public Long getTime() {
        return time;
    }

    @SuppressWarnings("unused")
    private void setTime(Long time) {
        this.time = time;
    }
}
