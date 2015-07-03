package com.echatman.nextbus.response.messages;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author echatman
 */
@XmlType
public class RouteMessages {

    private List<Message> messages = new ArrayList<>();
    private String tag;

    @XmlElement(name = "message")
    public List<Message> getMessages() {
        return messages;
    }

    @XmlAttribute
    public String getTag() {
        return tag;
    }

    @SuppressWarnings("unused")
    private void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @SuppressWarnings("unused")
    private void setTag(String tag) {
        this.tag = tag;
    }

}
