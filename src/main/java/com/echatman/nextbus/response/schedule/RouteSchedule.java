package com.echatman.nextbus.response.schedule;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author echatman
 */
@XmlType
public class RouteSchedule {

    private String direction;
    private List<StopTitle> header;
    private List<TableRow> row;
    private String scheduleClass;
    private String serviceClass;
    private String tag;
    private String title;

    @XmlAttribute
    public String getDirection() {
        return direction;
    }

    @XmlElementWrapper(name = "header")
    @XmlElement(name = "stop")
    public List<StopTitle> getHeader() {
        return header;
    }

    @XmlElement(name = "tr")
    public List<TableRow> getRow() {
        return row;
    }

    @XmlAttribute
    public String getScheduleClass() {
        return scheduleClass;
    }

    @XmlAttribute
    public String getServiceClass() {
        return serviceClass;
    }

    @XmlAttribute
    public String getTag() {
        return tag;
    }

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    private void setDirection(String direction) {
        this.direction = direction;
    }

    @SuppressWarnings("unused")
    private void setHeader(List<StopTitle> header) {
        this.header = header;
    }

    @SuppressWarnings("unused")
    private void setRow(List<TableRow> row) {
        this.row = row;
    }

    @SuppressWarnings("unused")
    private void setScheduleClass(String scheduleClass) {
        this.scheduleClass = scheduleClass;
    }

    @SuppressWarnings("unused")
    private void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
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
