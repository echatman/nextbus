package com.echatman.nextbus.response.messages;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author echatman
 */
@XmlType
public class Message {

    private String creator;
    private Long endBoundary;
    private String endBoundaryStr;
    private String id;
    private List<Interval> intervals = new ArrayList<>();
    private String phonemeText;
    private Short priorityElement;
    private String priorityAttribute;
    private List<RouteConfiguredForMessage> routesConfiguredForMessage = new ArrayList<>();
    private Boolean sendToBuses;
    private String smsText;
    private Long startBoundary;
    private String startBoundaryStr;
    private String text;
    private String textSecondaryLanguage;

    @XmlAttribute
    public String getCreator() {
        return creator;
    }

    @XmlAttribute
    public Long getEndBoundary() {
        return endBoundary;
    }

    @XmlAttribute
    public String getEndBoundaryStr() {
        return endBoundaryStr;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    @XmlElement(name = "interval")
    public List<Interval> getIntervals() {
        return intervals;
    }

    @XmlElement
    public String getPhonemeText() {
        return phonemeText;
    }

    /**
     * Priority element as described by the documentation (numeric)
     */
    @XmlElement(name = "priority")
    public Short getPriorityElement() {
        return priorityElement;
    }

    /**
     * Priority attribute string as observed from actual API responses ("Low", "Medium", "High").
     *
     * @return
     */
    @XmlAttribute(name = "priority")
    public String getPriorityAttribute() {
        return priorityAttribute;
    }

    @XmlElement(name = "routeConfiguredForMessage")
    public List<RouteConfiguredForMessage> getRoutesConfiguredForMessage() {
        return routesConfiguredForMessage;
    }

    @XmlAttribute
    public Boolean getSendToBuses() {
        return sendToBuses;
    }

    @XmlElement
    public String getSmsText() {
        return smsText;
    }

    @XmlAttribute
    public Long getStartBoundary() {
        return startBoundary;
    }

    @XmlAttribute
    public String getStartBoundaryStr() {
        return startBoundaryStr;
    }

    @XmlElement
    public String getText() {
        return text;
    }

    @XmlElement
    public String getTextSecondaryLanguage() {
        return textSecondaryLanguage;
    }

    @SuppressWarnings("unused")
    private void setCreator(String creator) {
        this.creator = creator;
    }

    @SuppressWarnings("unused")
    private void setEndBoundary(Long endBoundary) {
        this.endBoundary = endBoundary;
    }

    @SuppressWarnings("unused")
    private void setEndBoundaryStr(String endBoundaryStr) {
        this.endBoundaryStr = endBoundaryStr;
    }

    @SuppressWarnings("unused")
    private void setId(String id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    private void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }

    @SuppressWarnings("unused")
    private void setPhonemeText(String phonemeText) {
        this.phonemeText = phonemeText;
    }

    @SuppressWarnings("unused")
    private void setPriorityElement(Short priorityElement) {
        this.priorityElement = priorityElement;
    }

    @SuppressWarnings("unused")
    private void setPriorityAttribute(String priorityAttribute) {
        this.priorityAttribute = priorityAttribute;
    }

    @SuppressWarnings("unused")
    private void setRoutesConfiguredForMessage(List<RouteConfiguredForMessage> routesConfiguredForMessage) {
        this.routesConfiguredForMessage = routesConfiguredForMessage;
    }

    @SuppressWarnings("unused")
    private void setSendToBuses(Boolean sendToBuses) {
        this.sendToBuses = sendToBuses;
    }

    @SuppressWarnings("unused")
    private void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    @SuppressWarnings("unused")
    private void setStartBoundary(Long startBoundary) {
        this.startBoundary = startBoundary;
    }

    @SuppressWarnings("unused")
    private void setStartBoundaryStr(String startBoundaryStr) {
        this.startBoundaryStr = startBoundaryStr;
    }

    @SuppressWarnings("unused")
    private void setText(String text) {
        this.text = text;
    }

    @SuppressWarnings("unused")
    private void setTextSecondaryLanguage(String textSecondaryLanguage) {
        this.textSecondaryLanguage = textSecondaryLanguage;
    }

}
