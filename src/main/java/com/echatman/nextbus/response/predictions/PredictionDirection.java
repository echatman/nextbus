package com.echatman.nextbus.response.predictions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

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
public class PredictionDirection {

    private List<Prediction> predictions;
    private String title;

    /**
     * No more than 5 predictions per direction will be provided in the
     * feed.
     */
    @XmlElement(name = "prediction")
    public List<Prediction> getPredictions() {
        return predictions;
    }

    /**
     * the name of the direction to be displayed in the User Interface,
     * such as “Inbound to Caltrain Station”.
     */
    @XmlAttribute
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    private void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    @SuppressWarnings("unused")
    private void setTitle(String title) {
        this.title = title;
    }

}
