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

    @XmlElement(name = "route")
    List<Route> routes;
    @XmlElement(name = "Error")
    Error error;

    @XmlType
    public static class Route {
        @XmlAttribute
        String tag;
        @XmlElement(name = "message")
        List<Message> messages;

        @XmlType
        public static class Message {
            @XmlAttribute
            String id;
            @XmlAttribute
            String creator;
            @XmlAttribute
            Boolean sendToBuses;
            @XmlAttribute
            Long startBoundary;
            @XmlAttribute
            String startBoundaryStr;
            @XmlAttribute
            Long endBoundary;
            @XmlAttribute
            String endBoundaryStr;
            @XmlElement(name = "interval")
            List<Interval> intervals;
            @XmlElement(name = "routeConfiguredForMessage")
            List<RouteConfiguredForMessage> routesConfiguredForMessage;
            @XmlElement
            String text;
            @XmlElement
            String textSecondaryLanguage;
            @XmlElement
            String phonemeText;
            @XmlElement
            String smsText;
            @XmlElement
            Short priority;

            @XmlType
            public static class Interval {
                @XmlAttribute
                Short startDay;
                @XmlAttribute
                Integer startTime;
                @XmlAttribute
                Short endDay;
                @XmlAttribute
                Integer endTime;
            }

            @XmlType
            public static class RouteConfiguredForMessage {
                @XmlAttribute(name = "tag")
                String tag;
                @XmlElement(name = "stop")
                List<Stop> stops;

                @XmlType
                public static class Stop {
                    @XmlAttribute
                    String tag;
                    @XmlAttribute
                    String title;
                }
            }
        }
    }
}
