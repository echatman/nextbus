package com.echatman.nextbus.response.predictions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * The predictions are returned in both seconds and minutes. The
 * “minute” value is what should currently be displayed. The
 * “seconds” value can be used to determine when the minute value
 * will change requiring an update. Predictions should only be
 * displayed in minutes, rounding down the number of seconds. The
 * predictions are also provided in “epochTime”. Epoch time is a
 * standard, defined as the number of seconds elapsed since midnight
 * Coordinated Universal Time (UTC) of January 1, 1970, not counting
 * leap seconds. It is useful for when one needs to display the
 * prediction time as a time of day, such as “4:15pm”.
 *
 * @author echatman
 */
@XmlType
public class Prediction {

    private Boolean affectedByLayover;
    private String block;
    private String branch;
    private Boolean delayed;
    private String dirTag;
    private Long epochTime;
    private Boolean isDeparture;
    private Boolean isScheduleBased;
    private Short minutes;
    private Short seconds;
    private String slowness;
    private String tripTag;
    private String vehicle;
    private String vehiclesInConsist;

    /**
     * Specifies whether the predictions are based not just on the
     * position of the vehicle and the expected travel time, but
     * also on whether a vehicle leaves a terminal at the configured
     * layover time. This information can be useful to passengers
     * because predictions that are affected by a layover will not
     * be as accurate. This element is only included in the XML feed
     * when the value is true. If the value is not set then it
     * should be considered false.
     */
    @XmlAttribute
    public Boolean getAffectedByLayover() {
        return affectedByLayover;
    }

    /**
     * Specifies the block number assigned to the vehicle as defined
     * in the configuration data.
     */
    @XmlAttribute
    public String getBlock() {
        return block;
    }

    /**
     * This attribute is provided only for the Toronto TTC agency.
     * For TTC there are many routes with multiple “branches”, where
     * the route has different paths. For route 107 for example
     * there can be branches “107”, “107A”, “107B”, “107C”, etc. By
     * using the branch information in the User Interface the
     * passengers can see if a prediction is for a bus that is going
     * on their desired branch.
     */
    @XmlAttribute
    public String getBranch() {
        return branch;
    }

    /**
     * Specifies if the bus is not traveling as fast as expected
     * over the last few minutes. This is useful for determining if
     * a vehicle is stuck in traffic such that the predictions might
     * not be as accurate. This feature is only enabled for certain
     * agencies. This element is only included in the XML feed when
     * the value is true. If the value is not set then it should be
     * considered false.
     */
    @XmlAttribute
    public Boolean getDelayed() {
        return delayed;
    }

    /**
     * Specifies the ID of the direction for the stop that the
     * prediction is for (as opposed to the direction that the
     * vehicle is currently on). The direction ID is usually the
     * same as a trip pattern ID, but is very different from the
     * tripTag. A direction or trip pattern ID specifies the
     * configuration for a trip. It can be used multiple times for a
     * block assignment. But a tripTag identifies a particular trip
     * within a block assignment.
     */
    @XmlAttribute
    public String getDirTag() {
        return dirTag;
    }

    /**
     * The predictions are also provided in “epochTime”. Epoch time
     * is a standard, defined as the number of seconds elapsed since
     * midnight Coordinated Universal Time (UTC) of January 1, 1970,
     * not counting leap seconds. It is useful for when one needs to
     * display the prediction time as a time of day, such as
     * “4:15pm”.
     */
    @XmlAttribute
    public Long getEpochTime() {
        return epochTime;
    }

    /**
     * For most stops predictions of when the bus will arrive are
     * provided. But there are some stops, especially stops at the
     * beginning of a trip, where there is a layover. This means
     * that the bus is stopped for at least several minutes. For
     * these situations the departure time is provided because a
     * passenger is only interested in the departure time for such
     * situations. Toronto TTC streetcars are an exception. At
     * terminals arrival predictions are provided for TTC streetcars
     * since they are managed more by headway instead of schedule.
     * In order to specify whether a prediction is for a departure
     * as opposed to an arrival time the additional tag element
     * “isDeparture” is provided along with the predictions. If it
     * is set to true then the prediction is for the departure time.
     * Otherwise the prediction is for an arrival time. For
     * departures NextBus uses the schedule to determine when the
     * vehicle will be leaving the stop if the vehicle is on-time or
     * running early. If the vehicle is running late then the
     * departure time will be based on the vehicles arrival time
     * (assuming that the vehicle will depart as soon as it arrives
     * without any layover).
     */
    @XmlAttribute
    public Boolean getIsDeparture() {
        return isDeparture;
    }

    /**
     * Specifies whether the predictions are based solely on the
     * schedule and do not take the GPS position of the vehicle into
     * account. This feature is not currently available for TTC.
     * This element is only included in the XML feed when the value
     * is true. If the value is not set then it should be considered
     * false.
     */
    @XmlAttribute
    public Boolean getIsScheduleBased() {
        return isScheduleBased;
    }

    /**
     * The “minute” value is what should currently be displayed.
     * Predictions should only be displayed in minutes, rounding
     * down the number of seconds.
     */
    @XmlAttribute
    public Short getMinutes() {
        return minutes;
    }

    /**
     * The “seconds” value can be used to determine when the minute
     * value will change requiring an update. Predictions should
     * only be displayed in minutes, rounding down the number of
     * seconds.
     */
    @XmlAttribute
    public Short getSeconds() {
        return seconds;
    }

    /**
     * A “slowness” value is also provided when a vehicle is delayed
     * but this attribute is experimental and might be removed from
     * the feed. The slowness attribute indicates how slow a vehicle
     * is traveling over the last few minutes compared to normal.
     */
    @XmlAttribute
    public String getSlowness() {
        return slowness;
    }

    /**
     * Specifies the ID of the trip for when the vehicle will be
     * arriving at the stop, as defined in the configuration data.
     * This element is only included in the XML feed if the trip ID
     * is actually included in the configuration data.
     */
    @XmlAttribute
    public String getTripTag() {
        return tripTag;
    }

    @XmlAttribute
    public String getVehicle() {
        return vehicle;
    }

    @XmlAttribute
    public String getVehiclesInConsist() {
        return vehiclesInConsist;
    }

    @SuppressWarnings("unused")
    private void setAffectedByLayover(Boolean affectedByLayover) {
        this.affectedByLayover = affectedByLayover;
    }

    @SuppressWarnings("unused")
    private void setBlock(String block) {
        this.block = block;
    }

    @SuppressWarnings("unused")
    private void setBranch(String branch) {
        this.branch = branch;
    }

    @SuppressWarnings("unused")
    private void setDelayed(Boolean delayed) {
        this.delayed = delayed;
    }

    @SuppressWarnings("unused")
    private void setDirTag(String dirTag) {
        this.dirTag = dirTag;
    }

    @SuppressWarnings("unused")
    private void setEpochTime(Long epochTime) {
        this.epochTime = epochTime;
    }

    @SuppressWarnings("unused")
    private void setIsDeparture(Boolean isDeparture) {
        this.isDeparture = isDeparture;
    }

    @SuppressWarnings("unused")
    private void setIsScheduleBased(Boolean isScheduleBased) {
        this.isScheduleBased = isScheduleBased;
    }

    @SuppressWarnings("unused")
    private void setMinutes(Short minutes) {
        this.minutes = minutes;
    }

    @SuppressWarnings("unused")
    private void setSeconds(Short seconds) {
        this.seconds = seconds;
    }

    @SuppressWarnings("unused")
    private void setSlowness(String slowness) {
        this.slowness = slowness;
    }

    @SuppressWarnings("unused")
    private void setTripTag(String tripTag) {
        this.tripTag = tripTag;
    }

    @SuppressWarnings("unused")
    private void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @SuppressWarnings("unused")
    private void setVehiclesInConsist(String vehiclesInConsist) {
        this.vehiclesInConsist = vehiclesInConsist;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "affectedByLayover=" + affectedByLayover +
                ", block='" + block + '\'' +
                ", branch='" + branch + '\'' +
                ", delayed=" + delayed +
                ", dirTag='" + dirTag + '\'' +
                ", epochTime=" + epochTime +
                ", isDeparture=" + isDeparture +
                ", isScheduleBased=" + isScheduleBased +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", slowness='" + slowness + '\'' +
                ", tripTag='" + tripTag + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", vehiclesInConsist='" + vehiclesInConsist + '\'' +
                '}';
    }
}
