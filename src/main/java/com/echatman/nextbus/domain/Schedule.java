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

    @XmlElement(name = "route")
    List<Route> routes;
    @XmlElement(name = "Error")
    Error error;

    @XmlType
    public static class Route {
        @XmlAttribute
        String tag;
        @XmlAttribute
        String title;
        @XmlAttribute
        String scheduleClass;
        @XmlAttribute
        String serviceClass;
        @XmlAttribute
        String direction;
        @XmlElementWrapper(name = "header")
        @XmlElement(name = "stop")
        List<StopTitle> header;
        @XmlElement(name = "tr")
        List<TableRow> row;

        @XmlType
        public static class StopTitle {
            @XmlAttribute
            @XmlID
            String tag;
            @XmlValue
            String title;
        }

        @XmlType
        public static class TableRow {
            @XmlAttribute
            String blockID;
            @XmlElement(name = "stop")
            List<Stop> stops;
        }

        @XmlType
        public static class Stop {
            @XmlAttribute(name="tag")
            @XmlIDREF
            StopTitle title;
            @XmlAttribute
            Long epochTime;
            @XmlValue
            String time;
        }
    }
}
