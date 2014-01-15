// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=sf-muni&r=N
 * @author echatman
 */
@XmlRootElement(name = "body")
@XmlType(name = "RouteConfig")
public class RouteConfig {
    @XmlElement(name="route")
    List<Route> routes;
    @XmlElement
    Error error;
}
