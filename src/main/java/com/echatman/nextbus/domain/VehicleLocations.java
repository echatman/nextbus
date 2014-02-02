// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <h1>Command "vehicleLocations"</h1>
 * <p>
 * To obtain a list of vehicle locations that have changed since the last time
 * the vehicleLocations command was used, use the "vehicleLocations" command.
 * The agency is specified by the "a" parameter in the query string. The tag for
 * the agency as obtained from the agencyList command should be used. The route
 * is specified by the "r" parameter. The tag for the route is obtained using
 * the routeList command. The "t" parameter specifies the last time that was
 * returned by the vehicleLocations command. The time in msec since the 1970
 * epoch time. If you specify a time of 0, then data for the last 15 minutes is
 * provided.
 * <p>
 * The format of the command for obtaining vehicle location is:
 * <p>
 * {@code http://webservices.nextbus.com/service/publicXMLFeed?command=vehicleLocations&a=<agency_tag>&r=<route tag>&t=<epoch time in msec> }
 * <p>
 * Attributes contained in the vehicle locations output are:
 * <ul>
 * <li><b><b>id</b></b> (string) – Identifier of the vehicle. It is often but
 * not always numeric.
 * <li><b>routeTag</b> (string) - Specifies the ID of the route the vehicle is
 * currently associated with.
 * <li><b>dirTag</b> (string) - Specifies the ID of the direction that the
 * vehicle is currently on. The direction ID is usually the same as a trip
 * pattern ID, but is very different from the tripTag. A direction or trip
 * pattern ID specifies the configuration for a trip. It can be used multiple
 * times for a block assignment. But a tripTag identifies a particular trip
 * within a block assignment.
 * <li><b>lat/lon</b> – specify the location of the vehicle.
 * <li><b>secsSinceReport</b> (int) – How many seconds since the GPS location
 * was actually recorded. It should be noted that sometimes a GPS report can be
 * several minutes old.
 * <li><b>predictable</b> (boolean) – Specifies whether the vehicle is currently
 * predictable.
 * <li><b>heading</b> (int) – Specifies the heading of the vehicle in degrees.
 * Will be a value between 0 and 360. A negative value indicates that the
 * heading is not currently available.
 * <li><b>speedKmHr</b> (double) – Specifies GPS based speed of vehicle.
 * </ul>
 * <p>
 * There is also a lastTime element that contains the update time of the last
 * vehicle location returned. This value can be used as the “t” parameter for
 * the next call to the vehicleLocations command so that only GPS reports since
 * the last time the command was called will be returned. This is useful for
 * preventing reading in vehicle locations that have not changed, reducing
 * bandwidth required. The single attribute for the lastTime element is:
 * <ul>
 * <li><b>time</b> (msec since 1970 epoch time) – Specifies time of the last
 * update for the vehicle location data returned.
 * </ul>
 * <p>
 * For the example URL:
 * <p>
 * <a href=
 * "http://webservices.nextbus.com/service/publicXMLFeed?command=vehicleLocations&a=sf-muni&r=N&t=1144953500233"
 * >http://webservices.nextbus.com/service/publicXMLFeed?command=
 * vehicleLocations&a=sf-muni&r=N&t=1144953500233</a>
 * <p>
 * The resulting XML is in the form:
 * <p>
 * 
 * <pre>
 * {@code
 * <body>
 *     <vehicle id="1453" routeTag="N" dirTag="out" lat="37.7664199" lon="-122.44896" secsSinceReport="29" predictable="true" heading="276" />
 *     <vehicle id="1549" routeTag="N" dirTag="in" lat="37.77631" lon="-122.3941" secsSinceReport="3" predictable="true" heading="45" />
 *     <vehicle id="1517" routeTag="N" dirTag="in_short" lat="37.76035" lon="-122.50794" secsSinceReport="69" predictable="true" heading="267" />
 *     <vehicle id="1547" routeTag="N" dirTag="out" lat="37.76952" lon="-122.43174" secsSinceReport="28" predictable="true" heading="85" />
 *     <vehicle id="1404" routeTag="N" dirTag="out" lat="37.76003" lon="-122.50919" secsSinceReport="9" predictable="true" heading="117" />
 *     <vehicle id="1400" routeTag="N" dirTag="in" lat="37.76415" lon="-122.46409" secsSinceReport="50" predictable="true" heading="266" />
 *     <lastTime time="1144953510433" />
 * </body>
 * }
 * </pre>
 * 
 * @author echatman
 */
@XmlRootElement(name = "body")
@XmlType
public class VehicleLocations {

    private String copyright;
    private Error error;
    private LastTime lastTime;
    private List<Vehicle> vehicles;

    @XmlAttribute
    public String getCopyright() {
        return copyright;
    }

    /**
     * @return the error
     */
    @XmlElement(name = "Error")
    public Error getError() {
        return error;
    }

    /**
     * @return the lastTime
     */
    @XmlElement
    public LastTime getLastTime() {
        return lastTime;
    }

    /**
     * @return the vehicles
     */
    @XmlElement(name = "vehicle")
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @SuppressWarnings("unused")
    private void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @SuppressWarnings("unused")
    private void setError(Error error) {
        this.error = error;
    }

    @SuppressWarnings("unused")
    private void setLastTime(LastTime lastTime) {
        this.lastTime = lastTime;
    }

    @SuppressWarnings("unused")
    private void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @XmlType
    public static class LastTime {

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

    @XmlType
    public static class Vehicle {

        private String dirTag;
        private Short heading;
        private String id;
        private BigDecimal lat;
        private BigDecimal lon;
        private Boolean predictable;
        private String routeTag;
        private Integer secsSinceReport;
        private Double speedKmHr;

        /**
         * Specifies the ID of the direction that the vehicle is currently on.
         * The direction ID is usually the same as a trip pattern ID, but is
         * very different from the tripTag. A direction or trip pattern ID
         * specifies the configuration for a trip. It can be used multiple times
         * for a block assignment. But a tripTag identifies a particular trip
         * within a block assignment.
         */
        @XmlAttribute
        public String getDirTag() {
            return dirTag;
        }

        /**
         * Specifies the heading of the vehicle in degrees. Will be a value
         * between 0 and 360. A negative value indicates that the heading is not
         * currently available.
         */
        @XmlAttribute
        public Short getHeading() {
            return heading;
        }

        /**
         * Identifier of the vehicle. It is often but not always numeric.
         */
        @XmlAttribute
        public String getId() {
            return id;
        }

        /**
         * specify the location of the vehicle.
         */
        @XmlAttribute
        public BigDecimal getLat() {
            return lat;
        }

        /**
         * specify the location of the vehicle.
         */
        @XmlAttribute
        public BigDecimal getLon() {
            return lon;
        }

        /**
         * Specifies whether the vehicle is currently predictable.
         */
        @XmlAttribute
        public Boolean getPredictable() {
            return predictable;
        }

        /**
         * Specifies the ID of the route the vehicle is currently associated
         * with.
         */
        @XmlAttribute
        public String getRouteTag() {
            return routeTag;
        }

        /**
         * How many seconds since the GPS location was actually recorded. It
         * should be noted that sometimes a GPS report can be several minutes
         * old.
         */
        @XmlAttribute
        public Integer getSecsSinceReport() {
            return secsSinceReport;
        }

        /**
         * Specifies GPS based speed of vehicle.
         */
        @XmlAttribute
        public Double getSpeedKmHr() {
            return speedKmHr;
        }

        @SuppressWarnings("unused")
        private void setDirTag(String dirTag) {
            this.dirTag = dirTag;
        }

        @SuppressWarnings("unused")
        private void setHeading(Short heading) {
            this.heading = heading;
        }

        @SuppressWarnings("unused")
        private void setId(String id) {
            this.id = id;
        }

        @SuppressWarnings("unused")
        private void setLat(BigDecimal lat) {
            this.lat = lat;
        }

        @SuppressWarnings("unused")
        private void setLon(BigDecimal lon) {
            this.lon = lon;
        }

        @SuppressWarnings("unused")
        private void setPredictable(Boolean predictable) {
            this.predictable = predictable;
        }

        @SuppressWarnings("unused")
        private void setRouteTag(String routeTag) {
            this.routeTag = routeTag;
        }

        @SuppressWarnings("unused")
        private void setSecsSinceReport(Integer secsSinceReport) {
            this.secsSinceReport = secsSinceReport;
        }

        @SuppressWarnings("unused")
        private void setSpeedKmHr(Double speedKmHr) {
            this.speedKmHr = speedKmHr;
        }
    }
}
