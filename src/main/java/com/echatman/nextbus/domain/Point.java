// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author echatman
 */
@XmlType
public class Point {
    @XmlAttribute
    BigDecimal lat;
    @XmlAttribute
    BigDecimal lon;
}
