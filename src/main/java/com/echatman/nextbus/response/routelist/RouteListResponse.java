// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.response.routelist;

import com.echatman.nextbus.response.NextBusResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * <h1>Command "routeList"</h1>
 * <p>
 * To obtain a list of routes for an agency, use the "routeList" command. The
 * agency is specified by the "a" parameter in the query string. The tag for the
 * agency as obtained from the agencyList command should be used.
 * <p>
 * The format of the command is:
 * <p>
 * {@code http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=<agency_tag> }
 * <p>
 * The route list data returned has multiple attributes. These are:
 * <ul>
 * <li><b>tag</b> – unique alphanumeric identifier for route, such as “N”.
 * <li><b>title</b> – the name of the route to be displayed in a User Interface,
 * such as “N-Judah”.
 * <li><b>shortTitle</b> – for some transit agencies shorter titles are provided
 * that can be useful for User Interfaces where there is not much screen real
 * estate, such as on smartphones. This element is only provided where a short
 * title is actually available. If a short title is not available then the
 * regular title element should be used.
 * </ul>
 * <p>
 * For the example URL:
 * <p>
 * <a href=
 * "http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=sf-muni"
 * >http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=sf-
 * muni</a>
 * <p>
 * The resulting XML is in the form:
 * <p>
 * 
 * <pre>
 * {@code
 * <body>
 *     <route tag="1" title="1 - California" shortTitle="1-Calif" />
 *     <route tag="3" title="3 - Jackson" shortTitle="3-Jacksn" />
 *     <route tag="4" title="4 - Sutter" shortTitle="4-Sutter" />
 *     <route tag="5" title="5 - Fulton" shortTitle="5-Fulton" />
 *     <route tag="6" title="6 - Parnassus" shortTitle="6-Parnas" />
 *     <route tag="7" title="7 - Haight" shortTitle="7-Haight" />
 *     <route tag="14" title="14 - Mission" shortTitle="14-Missn" />
 *     <route tag="21" title="21 - Hayes" shortTitle="21-Hayes" />
 * </body>
 * }
 * </pre>
 * 
 * @author echatman
 */
@XmlType
public class RouteListResponse extends NextBusResponse {

    private List<Route> routes;

    @XmlElement(name = "route")
    public List<Route> getRoutes() {
        return routes;
    }

    @SuppressWarnings("unused")
    private void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}
