package com.echatman.nextbus.response.routeconfig;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author echatman
 */
@XmlType
public class Tag {

    private String id;

    @XmlAttribute
    public String getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public void setId(String id) {
        this.id = id;
    }
}
