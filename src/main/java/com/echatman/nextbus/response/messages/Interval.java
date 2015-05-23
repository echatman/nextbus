package com.echatman.nextbus.response.messages;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author echatman
 */
@XmlType
public class Interval {

    private Short endDay;
    private Integer endTime;
    private Short startDay;
    private Integer startTime;

    @XmlAttribute
    public Short getEndDay() {
        return endDay;
    }

    @XmlAttribute
    public Integer getEndTime() {
        return endTime;
    }

    @XmlAttribute
    public Short getStartDay() {
        return startDay;
    }

    @XmlAttribute
    public Integer getStartTime() {
        return startTime;
    }

    @SuppressWarnings("unused")
    private void setEndDay(Short endDay) {
        this.endDay = endDay;
    }

    @SuppressWarnings("unused")
    private void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    @SuppressWarnings("unused")
    private void setStartDay(Short startDay) {
        this.startDay = startDay;
    }

    @SuppressWarnings("unused")
    private void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }
}
