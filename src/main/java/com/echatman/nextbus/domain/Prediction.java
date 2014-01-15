// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author echatman
 */
@XmlType
public class Prediction {
    @XmlAttribute
    Short seconds;
    @XmlAttribute
    Short minutes;
    @XmlAttribute
    Long epochTime;
    @XmlAttribute
    Boolean isDeparture;
    @XmlAttribute
    String block;
    @XmlAttribute
    String dirTag;
    @XmlAttribute
    String tripTag;
    @XmlAttribute
    String branch;
    @XmlAttribute
    Boolean affectedByLayover;
    @XmlAttribute
    Boolean isScheduleBased;
    @XmlAttribute
    Boolean delayed;
    @XmlAttribute
    String slowness;
}
