package com.echatman.nextbus.response.routeconfig;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author echatman
 */
@XmlType
public class Direction {

    private String name;
    private List<StopRef> stops;
    private String tag;
    private String title;
    private Boolean useForUI;

    /**
     * a simplified name so that directions can be grouped together. If
     * there are several Inbound directions for example then they can
     * all be grouped together because they will all have the same name
     * “Inbound”. This element is not available for all transit
     * agencies.
     */
    @XmlAttribute
    public String getName() {
        return name;
    }

    /**
     * within the direction there is a list of stops in order. This are
     * useful for creating a User Interface where the user selects a
     * route, direction, and then stop in order to obtain predictions.
     */
    @XmlElement(name = "stop")
    public List<StopRef> getStops() {
        return stops;
    }

    /**
     * unique alphanumeric identifier for the direction.
     */
    @XmlAttribute
    public String getTag() {
        return tag;
    }

    /**
     * the name of the direction to be displayed in the User Interface,
     * such as “Inbound to Caltrain Station”.
     */
    @XmlAttribute
    public String getTitle() {
        return title;
    }

    /**
     * If the direction is important enough to be listed to a passenger
     * then the “useForUI” element will be set to true. The other
     * directions typically do not need to be shown and are therefore
     * not provided by default. If you need the other non - useForUI
     * directions then you need to add “&verbose” to the URL.
     */
    @XmlAttribute
    public Boolean getUseForUI() {
        return useForUI;
    }

    @SuppressWarnings("unused")
    private void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    private void setStops(List<StopRef> stops) {
        this.stops = stops;
    }

    @SuppressWarnings("unused")
    private void setTag(String tag) {
        this.tag = tag;
    }

    @SuppressWarnings("unused")
    private void setTitle(String title) {
        this.title = title;
    }

    @SuppressWarnings("unused")
    private void setUseForUI(Boolean useForUI) {
        this.useForUI = useForUI;
    }

}
