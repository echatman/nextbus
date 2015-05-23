package com.echatman.nextbus.response.routelist;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author echatman
 */
@XmlType
public class Route {

    private String shortTitle;
    private String tag;
    private String title;

    /**
     * for some transit agencies shorter titles are provided that can be
     * useful for User Interfaces where there is not much screen real
     * estate, such as on smartphones. This element is only provided where a
     * short title is actually available. If a short title is not available
     * then the regular title element should be used.
     */
    @XmlAttribute
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * unique alphanumeric identifier for route, such as “N”.
     */
    @XmlAttribute
    public String getTag() {
        return tag;
    }

    /**
     * the name of the route to be displayed in a User Interface, such as “N
     * - Judah”.
     */
    @XmlAttribute
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    private void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
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
