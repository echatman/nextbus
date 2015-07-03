package com.echatman.nextbus.response.predictions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Within the <predictions> element are possibly multiple <direction>
 * elements and possibly multiple <message> elements.
 *
 * @author echatman
 */
@XmlType
public class Predictions {

    private String agencyTitle;
    private List<PredictionDirection> directions = new ArrayList<>();
    private String dirTitleBecauseNoPredictions;
    private List<PredictionMessage> messages = new ArrayList<>();
    private String routeTag;
    private String routeTitle;
    private String stopTitle;
    private String stopTag;

    /**
     * The name of the agency to be displayed to passenger.
     */
    @XmlAttribute
    public String getAgencyTitle() {
        return agencyTitle;
    }

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
    public List<PredictionDirection> getDirections() {
        return directions;
    }

    /**
     * Title of direction. This attribute is only provided if there are no
     * predictions. The direction title is provided in this situation
     * because no <direction> elements are available since there are no
     * predictions. This way the User Interface can still display the title
     * of the direction selected even when there are no predictions.
     */
    @XmlAttribute
    public String getDirTitleBecauseNoPredictions() {
        return dirTitleBecauseNoPredictions;
    }

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
    public List<PredictionMessage> getMessages() {
        return messages;
    }

    /**
     * Identifier for the route.
     */
    @XmlAttribute
    public String getRouteTag() {
        return routeTag;
    }

    /**
     * Title of the route to be displayed to passenger.
     */
    @XmlAttribute
    public String getRouteTitle() {
        return routeTitle;
    }

    /**
     * Title of the stop to be displayed to passenger.
     */
    @XmlAttribute
    public String getStopTitle() {
        return stopTitle;
    }

    /**
     * Not documented, but observed in real responses.
     */
    @XmlAttribute
    public String getStopTag() {
        return stopTag;
    }

    @SuppressWarnings("unused")
    private void setAgencyTitle(String agencyTitle) {
        this.agencyTitle = agencyTitle;
    }

    @SuppressWarnings("unused")
    private void setDirections(List<PredictionDirection> directions) {
        this.directions = directions;
    }

    @SuppressWarnings("unused")
    private void setDirTitleBecauseNoPredictions(String dirTitleBecauseNoPredictions) {
        this.dirTitleBecauseNoPredictions = dirTitleBecauseNoPredictions;
    }

    @SuppressWarnings("unused")
    private void setMessages(List<PredictionMessage> messages) {
        this.messages = messages;
    }

    @SuppressWarnings("unused")
    private void setRouteTag(String routeTag) {
        this.routeTag = routeTag;
    }

    @SuppressWarnings("unused")
    private void setRouteTitle(String routeTitle) {
        this.routeTitle = routeTitle;
    }

    @SuppressWarnings("unused")
    private void setStopTitle(String stopTitle) {
        this.stopTitle = stopTitle;
    }

    @SuppressWarnings("unused")
    private void setStopTag(String stopTag) {
        this.stopTag = stopTag;
    }

}
