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
@XmlType(name="RouteList")
public class RouteList {
    @XmlElement(name="route")
    List<Route> routes;
    @XmlElement
    Error error;
}
