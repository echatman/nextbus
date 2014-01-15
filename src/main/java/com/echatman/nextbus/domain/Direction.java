// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author echatman
 */
@XmlType
public class Direction {
    /**
     * unique alphanumeric identifier for the direction.
     */
    @XmlAttribute
    String tag;
    /**
     * the name of the direction to be displayed in the User Interface, such as
     * “Inbound to Caltrain Station”.
     */
    @XmlAttribute
    String title;
    /**
     * a simplified name so that directions can be grouped together. If there
     * are several Inbound directions for example then they can all be grouped
     * together because they will all have the same name “Inbound”. This element
     * is not available for all transit agencies.
     */
    @XmlAttribute
    String name;
    /**
     * If the direction is important enough to be listed to a passenger then the
     * “useForUI” element will be set to true. The other directions typically do
     * not need to be shown and are therefore not provided by default. If you
     * need the other non - useForUI directions then you need to add “&verbose”
     * to the URL.
     */
    @XmlAttribute
    Boolean useForUI;
    /**
     * within the direction there is a list of stops in order. This are useful
     * for creating a User Interface where the user selects a route, direction,
     * and then stop in order to obtain predictions.
     */
    @XmlIDREF
    List<Stop> stops;
    @XmlElement(name="prediction")
    List<Prediction> predictions;
}
