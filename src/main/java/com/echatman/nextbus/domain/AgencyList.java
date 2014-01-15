// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author echatman
 */
@XmlRootElement(name="body")
@XmlType(name="AgencyList")
public class AgencyList {
    @XmlElement(name="agency")
    List<Agency> agencies;
    @XmlElement(name="Error")
    Error error;
}
