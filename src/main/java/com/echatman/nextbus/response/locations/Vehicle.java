package com.echatman.nextbus.response.locations;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 * @author echatman
 */
@XmlType
public class Vehicle {

    private String dirTag;
    private Short heading;
    private String id;
    private BigDecimal lat;
    private BigDecimal lon;
    private Boolean predictable;
    private String routeTag;
    private Integer secsSinceReport;
    private BigDecimal speedKmHr;
    private String leadingVehicleId;
    private Integer passengerCount;

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
     * Specifies the location of the vehicle.
     */
    @XmlAttribute
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * Specifies the location of the vehicle.
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
    public BigDecimal getSpeedKmHr() {
        return speedKmHr;
    }

    /**
     * Not mentioned in the documentation. Sometimes included in the response.
     */
    @XmlAttribute
    public String getLeadingVehicleId() {
        return leadingVehicleId;
    }

    /**
     * Not mentioned in the documentation, but observed in the response for some agencies.
     */
    @XmlAttribute
    public Integer getPassengerCount() {
        return passengerCount;
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
    private void setSpeedKmHr(BigDecimal speedKmHr) {
        this.speedKmHr = speedKmHr;
    }

    @SuppressWarnings("unused")
    private void setLeadingVehicleId(String leadingVehicleId) {
        this.leadingVehicleId = leadingVehicleId;
    }

    @SuppressWarnings("unused")
    private void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }
}
