// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author echatman
 */
@XmlRootElement(name="body")
@XmlType
public class PredictionsBody {
    @XmlElement
    Predictions predictions;
    @XmlElement(name="Error")
    Error error;
}
