// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <h1>Command "schedule"</h1>
 * <p>
 * To obtain the schedule information for a route use the "schedule" command.
 * The agency is specified by the "a" parameter in the query string. The tag for
 * the agency as obtained from the agencyList command should be used. The route
 * is specified by the "r" parameter. The tag for the route is obtained using
 * the routeList command.
 * <p>
 * The format of the command is:
 * <p>
 * {@code http://webservices.nextbus.com/service/publicXMLFeed?command=schedule&a=<agency_tag>&r=<route_tag> }
 * <p>
 * The schedule data returned has multiple elements. It begins with a route
 * element that has these attributes:
 * <ul>
 * <li><b>tag</b> - unique alphanumeric identifier for the route, such as "N".
 * <li><b>title</b> - the name of the route to be displayed in a User Interface,
 * such as "N-Judah".
 * <li><b>scheduleClass</b> - name of the current schedule class, which may
 * change with the seasons (e.g. fall and spring schedules).
 * <li><b>serviceClass</b> - indicates service date(s) when the schedule
 * applies, which may differ on weekdays, weekend days, and holidays.
 * <li><b>direction</b> - simplified name for travel directions, grouped
 * together.
 * </ul>
 * <p>
 * This is followed by a header element that contains a number of stop elements.
 * These stop elements have one attribute and content:
 * <ul>
 * <li><b>tag</b> - unique alphanumeric identifier for the stop, such as "5225".
 * Even if the stop tags appear to usually be numeric they can sometimes contain
 * alphabetical characters.
 * <li>The content of the element is a title that can be displayed in a User
 * Interface, such as "Judah St & Sunset Blvd".
 * </ul>
 * <p>
 * This is followed by a number of tr elements, which have one attribute:
 * <ul>
 * <li><b>blockID</b> - specifies the block number as defined in the
 * configuration data.
 * </ul>
 * <p>
 * Each tr element has multiple stop elements. The stop elements have these
 * attributes and content:
 * <ul>
 * <li><b>tag</b> - unique identifier for the stop.
 * <li><b>epochTime</b> - scheduled arrival as epoch time (see above).
 * <li>The content of the element is the time in HH:mm:ss format, which could be
 * displayed in a User Interface. For trips where a vehicle does not serve the
 * stop the epochTime is set to -1 and the time data is output as “—“.
 * </ul>
 * <p>
 * For the example URL:
 * <p>
 * <a href=
 * "http://webservices.nextbus.com/service/publicXMLFeed?command=schedule&a=sf-muni&r=N"
 * >http://webservices.nextbus.com/service/publicXMLFeed?command=schedule&a=sf-
 * muni&r=N</a>
 * <p>
 * The resulting XML is in the form:
 * <p>
 * 
 * <pre>
 * {@code
 * <body>
 *     <route tag="N" title="N-Judah" scheduleClass="2011JANUARY" serviceClass="wkd" direction="Inbound">
 *         <header>
 *             <stop tag="5225">Judah St and Sunset Blvd</stop>
 *             <stop tag="5200">Judah St and 19th Ave</stop>
 *             <stop tag="3913">Carl St and Hillway Ave</stop>
 *             <!-- ... -->
 *         </header>
 *         <tr blockID="9721">
 *             <stop tag="5225" epochTime="540000">00:09:00</stop>
 *             <stop tag="5200" epochTime="840000">00:14:00</stop>
 *             <stop tag="3913" epochTime="1440000">00:24:00</stop>
 *             <!-- ... -->
 *         </tr>
 *         <tr blockID="9703">
 *             <stop tag="5225" epochTime="1680000">00:28:00</stop>
 *             <stop tag="5200" epochTime="1980000">00:33:00</stop>
 *             <stop tag="3913" epochTime="2460000">00:41:00</stop>
 *             <!-- ... -->
 *         </tr>
 *         <!-- ... -->
 *     </route>
 * </body>
 * }
 * </pre>
 * <p>
 * This form differs from that of our other commands, in that its structure
 * parallels that of an HTML table. The above example could very easily be
 * reworked into this:
 * <p>
 * 
 * <pre>
 * {@code
 * <table>
 *     <tr>
 *         <th>Judah St and Sunset Blvd</th>
 *         <th>Judah St and 19th Ave</th>
 *         <th>Carl St and Hillway Ave</th>
 *         <!-- ... -->
 *     </tr>
 *         <td>12:09 am</td>
 *         <td>12:14 am</td>
 *         <td>12:24 am</td>
 *         <!-- ... -->
 *     </tr>
 *     <tr>
 *         <td>12:28 am</td>
 *         <td>12:33 am</td>
 *         <td>12:41 am</td>
 *         <!-- ... -->
 *     </tr>
 *     <!-- ... -->
 * </table>
 * }
 * </pre>
 * 
 * @author echatman
 */
@XmlRootElement(name = "body")
@XmlType
public class Schedule {

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

        @XmlType
        public static class Stop {

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

        @XmlType
        public static class StopTitle {

            private String tag;
            private String title;

            @XmlAttribute
            @XmlID
            public String getTag() {
                return tag;
            }

            @XmlValue
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

        @XmlType
        public static class TableRow {

            private String blockID;
            private List<Stop> stops;

            @XmlAttribute
            public String getBlockID() {
                return blockID;
            }

            @XmlElement(name = "stop")
            public List<Stop> getStops() {
                return stops;
            }

            @SuppressWarnings("unused")
            private void setBlockID(String blockID) {
                this.blockID = blockID;
            }

            @SuppressWarnings("unused")
            private void setStops(List<Stop> stops) {
                this.stops = stops;
            }
        }
    }
}
