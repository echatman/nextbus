// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author echatman
 */
@XmlType
public class Predictions {
    String agencyTitle;
    String routeTag;
    String routeTitle;
    String stopTitle;
    String dirTitleBecauseNoPredictions;
    @XmlElement(name="direction")
    List<Direction> directions;
    @XmlElement(name="message")
    List<Message> messages;
}
