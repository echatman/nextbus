package com.echatman.nextbus.response.predictions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

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
public class PredictionMessage {

    private String priority;
    private String text;

    @XmlAttribute
    public String getPriority() {
        return priority;
    }

    /**
     * The text of the message.
     */
    @XmlAttribute
    public String getText() {
        return text;
    }

    @SuppressWarnings("unused")
    private void setPriority(String priority) {
        this.priority = priority;
    }

    @SuppressWarnings("unused")
    private void setText(String text) {
        this.text = text;
    }
}
