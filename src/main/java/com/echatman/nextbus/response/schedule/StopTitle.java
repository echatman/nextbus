package com.echatman.nextbus.response.schedule;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author echatman
 */
@XmlType
public class StopTitle {

    private String tag;
    private String title;

    @XmlAttribute
    @XmlID
    public String getTag() {
        return tag;
    }

    @XmlValue
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
