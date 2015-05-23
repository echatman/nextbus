package com.echatman.nextbus.response.agencylist;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author echatman
 */
@XmlType
public class Agency {

    private String regionTitle;
    private String shortTitle;
    private String tag;
    private String title;

    @XmlAttribute
    public String getRegionTitle() {
        return regionTitle;
    }

    /**
     * The shortTitle element is provided only if it is different than the
     * standard title element. If no shortTitle element is provided, simply
     * use the standard title element
     */
    @XmlAttribute
    public String getShortTitle() {
        return shortTitle;
    }

    @XmlAttribute
    public String getTag() {
        return tag;
    }

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    @SuppressWarnings("unused")
    private void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
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
