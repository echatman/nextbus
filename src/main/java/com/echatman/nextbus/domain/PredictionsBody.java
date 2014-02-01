// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Predictions associated with a stop. Note: the predictions are grouped by
 * direction. For situations where buses on a line have different destinations
 * because some turn back earlier than others, the predictions presented to the
 * user can provide this important piece of information.
 * 
 * @author echatman
 */
@XmlRootElement(name = "body")
@XmlType
public class PredictionsBody {

    @XmlElement
    Predictions predictions;
    @XmlElement(name = "Error")
    Error error;

    /**
     * Within the <predictions> element are possibly multiple <direction>
     * elements and possibly multiple <message> elements.
     * 
     * @author echatman
     */
    @XmlType
    public static class Predictions {
        /**
         * The name of the agency to be displayed to passenger.
         */
        @XmlAttribute
        String agencyTitle;
        /**
         * Identifier for the route.
         */
        @XmlAttribute
        String routeTag;
        /**
         * Title of the route to be displayed to passenger.
         */
        @XmlAttribute
        String routeTitle;
        /**
         * Title of the stop to be displayed to passenger.
         */
        @XmlAttribute
        String stopTitle;
        /**
         * Title of direction. This attribute is only provided if there are no
         * predictions. The direction title is provided in this situation
         * because no <direction> elements are available since there are no
         * predictions. This way the User Interface can still display the title
         * of the direction selected even when there are no predictions.
         */
        @XmlAttribute
        String dirTitleBecauseNoPredictions;
        /**
         * Because routes can have multiple destinations the predictions are
         * separated by <direction> so the client can let the passenger know
         * whether the predicted for vehicles are actually going to their
         * destination. Then within the <direction> element a separate
         * <prediction> element is provided, one for each vehicle being
         * predicted. No more than 5 predictions per direction will be provided
         * in the feed.
         */
        @XmlElement(name = "direction")
        List<Direction> directions;
        /**
         * The <message> elements are important to handle because they provide
         * important status information to passengers. These messages might be
         * configured for an entire agency, for a route, or for a set of stops.
         * If more detailed information is required then should use the messages
         * command. Multiple messages can be returned for a single stop. The
         * only attribute in the message element is called text, which contains
         * the text of the message.
         */
        @XmlElement(name = "message")
        List<Message> messages;

        /**
         * Because routes can have multiple destinations the predictions are
         * separated by <direction> so the client can let the passenger know
         * whether the predicted for vehicles are actually going to their
         * destination. Then within the <direction> element a separate
         * <prediction> element is provided, one for each vehicle being
         * predicted. No more than 5 predictions per direction will be provided
         * in the feed.
         * 
         * @author echatman
         */
        @XmlType
        public static class Direction {
            /**
             * the name of the direction to be displayed in the User Interface,
             * such as “Inbound to Caltrain Station”.
             */
            @XmlAttribute
            String title;
            /**
             * No more than 5 predictions per direction will be provided in the
             * feed.
             */
            @XmlElement(name = "prediction")
            List<Prediction> predictions;

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
            public static class Prediction {
                /**
                 * The “minute” value is what should currently be displayed.
                 * Predictions should only be displayed in minutes, rounding
                 * down the number of seconds.
                 */
                @XmlAttribute
                Short minutes;
                /**
                 * The “seconds” value can be used to determine when the minute
                 * value will change requiring an update. Predictions should
                 * only be displayed in minutes, rounding down the number of
                 * seconds.
                 */
                @XmlAttribute
                Short seconds;
                /**
                 * The predictions are also provided in “epochTime”. Epoch time
                 * is a standard, defined as the number of seconds elapsed since
                 * midnight Coordinated Universal Time (UTC) of January 1, 1970,
                 * not counting leap seconds. It is useful for when one needs to
                 * display the prediction time as a time of day, such as
                 * “4:15pm”.
                 */
                @XmlAttribute
                Long epochTime;
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
                Boolean isDeparture;
                /**
                 * Specifies the block number assigned to the vehicle as defined
                 * in the configuration data.
                 */
                @XmlAttribute
                String block;
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
                String dirTag;
                /**
                 * Specifies the ID of the trip for when the vehicle will be
                 * arriving at the stop, as defined in the configuration data.
                 * This element is only included in the XML feed if the trip ID
                 * is actually included in the configuration data.
                 */
                @XmlAttribute
                String tripTag;
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
                String branch;
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
                Boolean affectedByLayover;
                /**
                 * Specifies whether the predictions are based solely on the
                 * schedule and do not take the GPS position of the vehicle into
                 * account. This feature is not currently available for TTC.
                 * This element is only included in the XML feed when the value
                 * is true. If the value is not set then it should be considered
                 * false.
                 */
                @XmlAttribute
                Boolean isScheduleBased;
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
                Boolean delayed;
                /**
                 * A “slowness” value is also provided when a vehicle is delayed
                 * but this attribute is experimental and might be removed from
                 * the feed. The slowness attribute indicates how slow a vehicle
                 * is traveling over the last few minutes compared to normal.
                 */
                @XmlAttribute
                String slowness;
                @XmlAttribute
                String vehicle;
                @XmlAttribute
                String vehiclesInConsist;
            }
        }

        /**
         * The <message> elements are important to handle because they provide
         * important status information to passengers. These messages might be
         * configured for an entire agency, for a route, or for a set of stops.
         * If more detailed information is required then should use the messages
         * command. Multiple messages can be returned for a single stop. The
         * only attribute in the message element is called text, which contains
         * the text of the message.
         * 
         * @author echatman
         */
        @XmlType
        public static class Message {
            /**
             * The text of the message.
             */
            @XmlAttribute
            String text;
            @XmlAttribute
            String priority;
        }
    }
}
