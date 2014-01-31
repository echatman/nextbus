// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <h1>Command "routeConfig"</h1>
 * <p>
 * To obtain a list of routes for an agency, use the "routeConfig" command. The
 * agency is specified by the "a" parameter in the query string. The tag for the
 * agency as obtained from the agencyList command should be used. The route is
 * optionally specified by the "r" parameter. The tag for the route is obtained
 * using the routeList command. If the "r" parameter is not specified, XML data
 * for all routes for the agency is returned. Due to the large size of the
 * resulting XML the routeConfig command is limited to providing data for only
 * up to 100 routes per request. If an agency has more than 100 routes then
 * multiple requests would need to be used to read data for all routes.
 * <p>
 * The format of the command is:
 * <p>
 * {@code http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=<agency_tag>&r=<route tag> }
 * <p>
 * The route data returned has multiple attributes. These are:
 * <ul>
 * <li><b>tag</b> – unique alphanumeric identifier for route, such as “N”.
 * <li><b>title</b> – the name of the route to be displayed in a User Interface,
 * such as “N-Judah”.
 * <li><b>shortTitle</b> – for some transit agencies shorter titles are provided
 * that can be useful for User Interfaces where there is not much screen real
 * estate, such as on smartphones. This element is only provided where a short
 * title is actually available. If a short title is not available then the
 * regular title element should be used.
 * <li><b>color</b> – the color in hexadecimal format associated with the route.
 * Useful for User Interfaces such as maps.
 * <li><b>oppositeColor</b> – the color that most contrasts with the route
 * color. Specified in hexadecimal format. Useful for User Interfaces such as
 * maps. Will be either black or white.
 * <li><b>latMin, latMax, lonMin, lonMax</b> – specifies the extent of the
 * route.
 * </ul>
 * <p>
 * The route data returned includes lists of stops, lists of directions, and
 * lists of paths. Configurations can be complicated, with multiple directions
 * having different sets of stops. Therefore one cannot expect the data to
 * contain just two simple directions for a route. The stops are provided to
 * show the details, such as the titles for the stops, lat/lon, and also a
 * numeric stop ID. If you are creating a map then you simply display all of the
 * stops on the map. But if you are creating route/direction/stop selection User
 * Interface you will need to use the direction data. The directions list all of
 * the stops associated with a direction. If the direction is important enough
 * to be listed to a passenger then the “useForUI” element will be set to true.
 * The other directions typically do not need to be shown and are therefore not
 * provided by default. If you need the other non- useForUI directions then you
 * need to add “&verbose” to the URL.
 * <p>
 * A stop has the following attributes:
 * <ul>
 * <li><b>tag</b> – unique alphanumeric identifier for stop, such as “cp_1321”.
 * Even if the stop tags appear to usually be numeric they can sometimes contain
 * alphabetical characters. Therefore the stop tags cannot be used as a number
 * for telephone systems and other such applications. For larger agencies such
 * as Toronto TTC suffixes "_IB" and “_OB" are included at the end of the stop
 * tag for the rare situations when an agency has defined only a single stop for
 * both directions and the stop is not an arrival at the end of the route (in
 * cases of arrivals “_ar” is used). This means that the stop tag might not
 * always correspond to GTFS or other configuration data. These suffixes allow
 * duplicate stops to have the identical stopID as the original stop while
 * preserving both unique stops in the system. "_IB" represents a duplicated
 * inbound stop, and "_OB" represents a duplicated outbound stop.
 * <li><b>title</b> – the name of the stop to displayed in a User Interface,
 * such as “5 St & Main, City Hall”.
 * <li><b>shortTitle</b> – some transit agencies define short version of the
 * title that are useful for applications where screen real estate is limited.
 * This element is only provided when a separate short title exists.
 * <li><b>lat/lon</b> – specify the location of the stop.
 * <li><b>stopId</b> – an optional numeric ID to identify a stop. Useful for
 * telephone or SMS systems so that a user can simply enter the numeric ID to
 * identify a stop instead of having to select a route, direction, and stop. Not
 * all transit agencies have numeric IDs to identify a stop so this element will
 * not always be available.
 * </ul>
 * <p>
 * A direction has the following attributes:
 * <ul>
 * <li><b>tag</b> – unique alphanumeric identifier for the direction.
 * <li><b>title</b> – the name of the direction to be displayed in the User
 * Interface, such as “Inbound to Caltrain Station”.
 * <li><b>name</b> – a simplified name so that directions can be grouped
 * together. If there are several Inbound directions for example then they can
 * all be grouped together because they will all have the same name “Inbound”.
 * This element is not available for all transit agencies.
 * <li><b>List of stops</b> – within the direction there is a list of stops in
 * order. This are useful for creating a User Interface where the user selects a
 * route, direction, and then stop in order to obtain predictions.
 * </ul>
 * <p>
 * The paths are simply lists of coordinates that can be used to draw a route on
 * a map. The path data can be voluminous. If you do not need the path data you
 * should add “&terse” to the routeConfig URL and the volume of returned data
 * will be cut approximately in half. This is especially useful for mobile apps
 * where you want to transfer as little data as possible.
 * <p>
 * Due to the nature of the configuration there can be many separate paths, some
 * of them overlapping. A map client should simply draw all of the paths. The
 * paths are not necessarily in any kind of order so you should only connect the
 * points within a path. You should not connect the points between two separate
 * paths though.
 * <p>
 * For the example URL:
 * <p>
 * <a href=
 * "http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=sf-muni&r=N"
 * >http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=
 * sf-muni&r=N</a>
 * <p>
 * the resulting XML is in the form:
 * <p>
 * 
 * <pre>
 * {@code
 * <body>
 *     <route tag="N" title="N-Judah" color="003399" oppositeColor="ffffff" latMin="37.7601699" latMax="37.7932299" lonMin="-122.5092" lonMax="-122.38798">
 *         <stop tag="KINGd4S0" title="King St and 4th St" shortTitle="King & 4th" lat="37.776036" lon="-122.394355" stopId="1" />
 *         <stop tag="KINGd2S0" title="King St and 2nd St" shortTitle="King & 2nd" lat="37.7796152" lon="-122.3898067" stopId="2" />
 *         <stop tag="EMBRBRAN" title="Embarcadero and Brannan St" shortTitle="Embarcadero & Brannan" lat="37.7844455" lon="-122.3880081" stopId="3" />
 *         <stop tag="EMBRFOLS" title="Embarcadero and Folsom St" shortTitle="Embarcadero & Folsom" lat="37.7905742" lon="-122.3896326" stopId="4" />
 *         <!-- ... -->
 *         <direction tag="out" title="Outboundto La Playa" name="Outbound" useForUI=" true ">
 *             <stop tag="KINGd4S0" />
 *             <stop tag="KINGd2S0" />
 *             <stop tag="EMBRBRAN" />
 *             <stop tag="EMBRFOLS" />
 *             <stop tag="CVCENTF" />
 *         </direction>
 *         <direction tag="in" title="Inboundto Caltrain" name="Inbound" useForUI=" true ">
 *             <stop tag="CVCENTF" />
 *             <stop tag="EMBRFOLS" />
 *             <stop tag="EMBRBRAN" />
 *             <stop tag="KINGd2S0" />
 *             <stop tag="KINGd4S0" />
 *         </direction>
 *         <direction tag="in_short" title="Short Run" name="Inbound" useForUI=" false ">
 *             <stop tag="CVCENTF" />
 *             <stop tag="EMBRFOLS" />
 *             <stop tag="EMBRBRAN" />
 *         </direction>
 *         <!-- ... -->
 *         <path>
 *             <point lat="37.7695171" lon="-122.4287571" />
 *             <point lat="37.7695099" lon="-122.42887" />
 *         </path>
 *         <path>
 *             <point lat="37.77551" lon="-122.39513" />
 *             <point lat="37.77449" lon="-122.39642" />
 *             <point lat="37.77413" lon="-122.39687" />
 *             <point lat="37.77385" lon="-122.39721" />
 *             <point lat="37.7737399" lon="-122.39734" />
 *             <point lat="37.77366" lon="-122.39744" />
 *             <point lat="37.77358" lon="-122.39754" />
 *             <point lat="37.77346" lon="-122.39766" />
 *             <point lat="37.77338" lon="-122.39772" />
 *             <point lat="37.77329" lon="-122.39778" />
 *             <point lat="37.77317" lon="-122.39784" />
 *         </path>
 *         <path>
 *             <point lat="37.76025" lon="-122.50927" />
 *             <point lat="37.76023" lon="-122.50928" />
 *             <point lat="37.76017" lon="-122.50928" />
 *             <point lat="37.7601299" lon="-122.50927" />
 *             <point lat="37.76008" lon="-122.50924" />
 *             <point lat="37.76006" lon="-122.50921" />
 *             <point lat="37.7600399" lon="-122.50916" />
 *             <point lat="37.76003" lon="-122.50912" />
 *             <point lat="37.7600399" lon="-122.50906" />
 *             <point lat="37.76005" lon="-122.50902" />
 *             <point lat="37.76008" lon="-122.50898" />
 *             <point lat="37.76017" lon="-122.50885" />
 *         </path>
 *         <!-- ... -->
 *     </route>
 * </body>
 * }
 * </pre>
 * 
 * @author echatman
 */
@XmlRootElement(name = "body")
@XmlType
public class RouteConfig {

    @XmlElement(name = "route")
    List<Route> routes;
    @XmlElement(name = "Error")
    Error error;

    @XmlType
    public static class Route {
        /**
         * unique alphanumeric identifier for route, such as “N”.
         */
        @XmlAttribute
        String tag;

        /**
         * the name of the route to be displayed in a User Interface, such as “N
         * - Judah”.
         */
        @XmlAttribute
        String title;
        /**
         * for some transit agencies shorter titles are provided that can be
         * useful for User Interfaces where there is not much screen real
         * estate, such as on smartphones. This element is only provided where a
         * short title is actually available. If a short title is not available
         * then the regular title element should be used.
         */
        @XmlAttribute
        String shortTitle;
        /**
         * the color in hexadecimal format associated with the route. Useful for
         * User Interfaces such as maps.
         */
        @XmlAttribute
        String color;
        /**
         * the color that most contrasts with the route color. Specified in
         * hexadecimal format. Useful for User Interfaces such as maps. Will be
         * either black or white.
         */
        @XmlAttribute
        String oppositeColor;
        /**
         * latMin , latMax , lonMin , lonMax – specifies the extent of the
         * route.
         */
        @XmlAttribute
        BigDecimal latMin;
        /**
         * latMin , latMax , lonMin , lonMax – specifies the extent of the
         * route.
         */
        @XmlAttribute
        BigDecimal latMax;
        /**
         * latMin , latMax , lonMin , lonMax – specifies the extent of the
         * route.
         */
        @XmlAttribute
        BigDecimal lonMin;
        /**
         * latMin , latMax , lonMin , lonMax – specifies the extent of the
         * route.
         */
        @XmlAttribute
        BigDecimal lonMax;
        @XmlElement(name = "stop")
        List<Stop> stops;
        @XmlElement(name = "direction")
        List<Direction> directions;
        /**
         * The paths are simply lists of coordinates that can be used to draw a
         * route on a map. The path data can be voluminous. If you do not need
         * the path data you should add “&terse” to the routeConfig URL and the
         * volume of returned data will be cut approximately in half. This is
         * especially useful for mobile apps where you want to transfer as
         * little data as possible. Due to the nature of the configuration there
         * can be many separate paths, some of them overlapping. A map client
         * should simply draw all of the paths. The paths are not necessa rily
         * in any kind of order so you should only connect the points within a
         * path. You should not connect the points between two separate paths
         * though.
         */
        @XmlElement(name = "path")
        List<Path> paths;

        @XmlType
        public static class Stop {
            /**
             * unique alphanumeric identifier for stop, such as “cp_1321”. Even
             * if the stop tags appear to usually be numeric they can sometimes
             * contain alphabetical characters. Therefore the stop tags cannot
             * be used as a number for telephone systems and other such
             * applications . For larger agencies such as Toronto TTC s uffixes
             * "_IB" and “_OB" are included at the end of the stop tag for the
             * rare situations when an agency has defined only a single stop for
             * both directions and the stop is not an arrival at the end of the
             * route (in cases of arrivals “_ar” is used). This means that the
             * stop tag might not always correspond to GTFS or other
             * configuration data. These suffixes allow duplicate stops to have
             * the identical stopID as the original stop while preserving both
             * unique stops in the system. "_IB" represents a duplicated inbound
             * stop, and "_OB" represents a duplicated outbound stop.
             */
            @XmlAttribute
            @XmlID
            String tag;
            /**
             * the name of the stop to displayed in a User Interface, such as “5
             * th St & Main, City Hall”.
             */
            @XmlAttribute
            String title;
            /**
             * some transit agencies define short version of the title that are
             * useful for applications where screen real estate is limited. This
             * element is only provided when a separate short title exists.
             */
            @XmlAttribute
            String shortTitle;
            /**
             * lat/lon - specify the location of the stop.
             */
            @XmlAttribute
            BigDecimal lat;
            /**
             * lat/lon - specify the location of the stop.
             */
            @XmlAttribute
            BigDecimal lon;
            /**
             * an optional numeric ID to identify a stop. Useful for telephone
             * or SMS systems so that a user can simply enter the numeric ID to
             * identify a stop instead of having to select a route, direction,
             * and stop. Not all transit agencies have numeric IDs to identify a
             * stop so this element will not always be available.
             */
            @XmlAttribute
            Long stopId;
        }

        @XmlType
        public static class Direction {
            /**
             * unique alphanumeric identifier for the direction.
             */
            @XmlAttribute
            String tag;
            /**
             * the name of the direction to be displayed in the User Interface,
             * such as “Inbound to Caltrain Station”.
             */
            @XmlAttribute
            String title;
            /**
             * a simplified name so that directions can be grouped together. If
             * there are several Inbound directions for example then they can
             * all be grouped together because they will all have the same name
             * “Inbound”. This element is not available for all transit
             * agencies.
             */
            @XmlAttribute
            String name;
            /**
             * If the direction is important enough to be listed to a passenger
             * then the “useForUI” element will be set to true. The other
             * directions typically do not need to be shown and are therefore
             * not provided by default. If you need the other non - useForUI
             * directions then you need to add “&verbose” to the URL.
             */
            @XmlAttribute
            Boolean useForUI;
            /**
             * within the direction there is a list of stops in order. This are
             * useful for creating a User Interface where the user selects a
             * route, direction, and then stop in order to obtain predictions.
             */
            @XmlElement(name = "stop")
            List<StopRef> stops;

            @XmlType
            public static class StopRef {
                @XmlAttribute(name = "tag")
                @XmlIDREF
                Stop stop;
            }
        }

        @XmlType
        public static class Path {
            @XmlElement(name = "point")
            List<Point> points;

            @XmlType
            public static class Point {
                @XmlAttribute
                BigDecimal lat;
                @XmlAttribute
                BigDecimal lon;
            }
        }
    }
}
