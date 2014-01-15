// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author echatman
 */
@XmlType(name="Agency")
public class Agency {
    @XmlAttribute
    String tag;
    @XmlAttribute
    String title;
    @XmlAttribute
    String regionTitle;
}
