package com.echatman.nextbus.response.messages;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author echatman
 */
@XmlType
public class Stop {

    private String tag;
    private String title;

    @XmlAttribute
    public String getTag() {
        return tag;
    }

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    private void setTag(String tag) {
        this.tag = tag;
    }

    @SuppressWarnings("unused")
    private void setTitle(String title) {
        this.title = title;
    }
}
