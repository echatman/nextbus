package com.echatman.nextbus.response.schedule;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author echatman
 */
@XmlType
public class ScheduledStop {

    private Long epochTime;
    private String time;
    private StopTitle title;

    @XmlAttribute
    public Long getEpochTime() {
        return epochTime;
    }

    @XmlValue
    public String getTime() {
        return time;
    }

    @XmlAttribute(name = "tag")
    @XmlIDREF
    public StopTitle getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    private void setEpochTime(Long epochTime) {
        this.epochTime = epochTime;
    }

    @SuppressWarnings("unused")
    private void setTime(String time) {
        this.time = time;
    }

    @SuppressWarnings("unused")
    private void setTitle(StopTitle title) {
        this.title = title;
    }
}
