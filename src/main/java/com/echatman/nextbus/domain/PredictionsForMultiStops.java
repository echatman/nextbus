// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <h1>Command "predictionsForMultiStops"</h1>
 * <p>
 * To obtain predictions associated with multiple stops use the
 * "predictionsForMultiStops" command. The agency is specified by the "a"
 * parameter in the query string. The tag for the agency as obtained from the
 * agencyList command should be used. The stops are specified by using the
 * "stops" parameter multiple times. Each stop is separated by the "|" character
 * and each stop is represented by a route and stop identifier, concatenated
 * together. One can also specify the useShortTitles=true parameter so that
 * shorter names for the agency, route, direction, and stop are returned if
 * shorter names are available. Shorter names can be useful for smaller displays
 * such as with wireless devices.
 * <p>
 * Currently the predictionsForMultiStops command can only be specified with
 * stop tags. In the future we expect to expand the feed so that the command can
 * also be used with stopIds. Predictions should only be displayed in minutes,
 * rounding down the number of seconds.
 * <p>
 * The format of the command for obtaining predictions for a list of stops is
 * (where a stop specified is a route tag and a stop tag separated by the "|"
 * character):
 * <p>
 * {@code http://webservices.nextbus.com/service/publicXMLFeed?command=predictionsForMultiStops&a=<agency_tag>&stops=<stop1>&stops=<stop2>&stops=<stop3> }
 * <p>
 * For the example URL:
 * <p>
 * <a href=
 * "http://webservices.nextbus.com/service/publicXMLFeed?command=predictionsForMultiStops&a=sf-muni&stops=N|6997&stops=N|3909"
 * >http://webservices.nextbus.com/service/publicXMLFeed?command=
 * predictionsForMultiStops&a=sf-muni&stops=N|6997&stops=N|3909</a>
 * <p>
 * The resulting XML will be in the form:
 * <p>
 * 
 * <pre>
 * {@code
 * <body>
 *     <direction title="Outbound toward Ocean Beach">
 *         <predictions stopTitle="Civic Center Station Outbound" routeCode="1" routeTitle="N - Judah">
 *             <prediction seconds="218" minutes="3" epochTime="1229637162309" isDeparture="false" />
 *             <prediction seconds="976" minutes="16" epochTime="122963716923" isDeparture="false" />
 *         </predictions>
 *     </direction>
 *     <direction title="Outbound toward Ocean Beach">
 *         <predictions stopTitle="Carl St and Cole St" routeCode="1" routeTitle="N - Judah">
 *             <prediction seconds="763" minutes="12" epochTime="1229637123422" isDeparture="false" />
 *             <prediction seconds="1281" minutes="21" epochTime="1229637168293" isDeparture="false" />
 *             <prediction seconds="1521" minutes="25" epochTime="1229637302334" isDeparture="false" />
 *             <prediction seconds="2027" minutes="33" epochTime="1229637430203" isDeparture="false" />
 *             <prediction seconds="2747" minutes="45" epochTime="1229637502034" isDeparture="false" />
 *         </predictions>
 *     </direction>
 * </body>
 * }
 * </pre>
 * 
 * @author echatman
 */
@XmlRootElement(name = "body")
@XmlType
public class PredictionsForMultiStops {

    private String copyright;
    private List<Direction> directions;
    private Error error;

    @XmlAttribute
    public String getCopyright() {
        return copyright;
    }

    /**
     * @return the directions
     */
    @XmlElement(name = "direction")
    public List<Direction> getDirections() {
        return directions;
    }

    /**
     * @return the error
     */
    @XmlElement(name = "Error")
    public Error getError() {
        return error;
    }

    @SuppressWarnings("unused")
    private void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @SuppressWarnings("unused")
    private void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    @SuppressWarnings("unused")
    private void setError(Error error) {
        this.error = error;
    }

    public static class Direction {

        private Predictions predictions;
        private String title;

        /**
         * @return the predictions
         */
        @XmlElement
        public Predictions getPredictions() {
            return predictions;
        }

        /**
         * @return the title
         */
        @XmlAttribute
        public String getTitle() {
            return title;
        }

        @SuppressWarnings("unused")
        private void setPredictions(Predictions predictions) {
            this.predictions = predictions;
        }

        @SuppressWarnings("unused")
        private void setTitle(String title) {
            this.title = title;
        }

        public static class Predictions {

            private List<Prediction> predictions;
            private String routeCode;
            private String routeTitle;
            private String stopTitle;

            /**
             * @return the predictions
             */
            @XmlElement(name = "prediction")
            public List<Prediction> getPredictions() {
                return predictions;
            }

            /**
             * @return the routeCode
             */
            @XmlAttribute
            public String getRouteCode() {
                return routeCode;
            }

            /**
             * @return the routeTitle
             */
            @XmlAttribute
            public String getRouteTitle() {
                return routeTitle;
            }

            /**
             * @return the stopTitle
             */
            @XmlAttribute
            public String getStopTitle() {
                return stopTitle;
            }

            @SuppressWarnings("unused")
            private void setPredictions(List<Prediction> predictions) {
                this.predictions = predictions;
            }

            @SuppressWarnings("unused")
            private void setRouteCode(String routeCode) {
                this.routeCode = routeCode;
            }

            @SuppressWarnings("unused")
            private void setRouteTitle(String routeTitle) {
                this.routeTitle = routeTitle;
            }

            @SuppressWarnings("unused")
            private void setStopTitle(String stopTitle) {
                this.stopTitle = stopTitle;
            }

            public static class Prediction {

                private Long epochTime;
                private Boolean isDeparture;
                private Integer minutes;
                private Integer seconds;

                /**
                 * @return the epochTime
                 */
                @XmlAttribute
                public Long getEpochTime() {
                    return epochTime;
                }

                /**
                 * @return the isDeparture
                 */
                @XmlAttribute
                public Boolean getIsDeparture() {
                    return isDeparture;
                }

                /**
                 * @return the minutes
                 */
                @XmlAttribute
                public Integer getMinutes() {
                    return minutes;
                }

                /**
                 * @return the seconds
                 */
                @XmlAttribute
                public Integer getSeconds() {
                    return seconds;
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
                private void setMinutes(Integer minutes) {
                    this.minutes = minutes;
                }

                @SuppressWarnings("unused")
                private void setSeconds(Integer seconds) {
                    this.seconds = seconds;
                }
            }
        }
    }
}
