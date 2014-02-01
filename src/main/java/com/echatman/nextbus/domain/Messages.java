// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author echatman
 */
@XmlRootElement(name = "body")
@XmlType
public class Messages {

    private Error error;
    private List<Route> routes;

    @XmlElement(name = "Error")
    public Error getError() {
        return error;
    }

    @XmlElement(name = "route")
    public List<Route> getRoutes() {
        return routes;
    }

    @SuppressWarnings("unused")
    private void setError(Error error) {
        this.error = error;
    }

    @SuppressWarnings("unused")
    private void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @XmlType
    public static class Route {

        private List<Message> messages;
        private String tag;

        @XmlElement(name = "message")
        public List<Message> getMessages() {
            return messages;
        }

        @XmlAttribute
        public String getTag() {
            return tag;
        }

        @SuppressWarnings("unused")
        private void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        @SuppressWarnings("unused")
        private void setTag(String tag) {
            this.tag = tag;
        }

        @XmlType
        public static class Message {

            private String creator;
            private Long endBoundary;
            private String endBoundaryStr;
            private String id;
            private List<Interval> intervals;
            private String phonemeText;
            private Short priority;
            private List<RouteConfiguredForMessage> routesConfiguredForMessage;
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

            @XmlElement
            public Short getPriority() {
                return priority;
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
            private void setPriority(Short priority) {
                this.priority = priority;
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

            @XmlType
            public static class Interval {

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

            @XmlType
            public static class RouteConfiguredForMessage {

                private List<Stop> stops;
                private String tag;

                @XmlElement(name = "stop")
                public List<Stop> getStops() {
                    return stops;
                }

                @XmlAttribute(name = "tag")
                public String getTag() {
                    return tag;
                }

                @SuppressWarnings("unused")
                private void setStops(List<Stop> stops) {
                    this.stops = stops;
                }

                @SuppressWarnings("unused")
                private void setTag(String tag) {
                    this.tag = tag;
                }

                @XmlType
                public static class Stop {

                    private String tag;
                    private String title;

                    @XmlAttribute
                    public String getTag() {
                        return tag;
                    }

                    @XmlAttribute
                    public String getTitle() {
                        return title;
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
            }
        }
    }
}
