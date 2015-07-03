// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.response.messages;

import com.echatman.nextbus.response.NextBusResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Command "messages"</h1>
 *
 * <p>
 * To obtain messages currently active for a route or a group of routes, use the "messages"
 * command. Note: this command only returns currently active messages. If a message is
 * configured just to be shown on Tuesdays for example but it is currently Wednesday then the
 * message will not be listed. The agency is specified by the "a" parameter in the query string. The
 * tag for the agency as obtained from the agencyList command should be used.
 *
 * <p>
 * The format of the command for obtaining messages for a route or a group of routes is shown
 * below. One can optionally specify route tags to get messages for particular routes. Multiple route
 * tags can be specified simply by specifying them separately in the url. For example, if you want
 * messages for routes "N" and "J" you would specify "&r=N&r=J". If a message is defined for
 * multiple routes then it will be listed for each route. If you do not specify a route tag then messages
 * for all routes will be returned.
 *
 * <pre>
 * http://webservices.nextbus.com/service/publicXMLFeed?command=messages&a=<agency tag>&r=<route tag1>&r=<route tagN>
 * </pre>
 *
 * The resulting XML is in the form shown below. MessagesResponse are grouped by route. The first group
 * will be for system wide messages. For such messages the route tag will be set to "all". If
 * messages were configured to be sent to specific routes then each message will contain a
 * <routeConfiguredForMessage> element. Since messages can be targeted to multiple routes each
 * message can contain multiple <routeConfiguredForMessage> element. If a message is
 * configured just for certain stops on a route then the <routeConfiguredForMessage> element will
 * contain a <stop> element for each stop.
 *
 * <p>
 * Each message includes a unique id for identifying the message. Each one also contains the login
 * of the person who created the message. The start boundary and end boundary are only provided
 * if the message was specified to have a start and stop time or a duration. If start and end
 * boundaries are not listed then the message is to be displayed until it is canceled.
 *
 * <p>
 * MessagesResponse contain a <text> element that specifies the text for the message. They can also contain
 * a <textSecondaryLanguage> element for when there is separate text in a different language for
 * the message. So for transit agencies in Quebec Canada the <text> element can have the French
 * text and the <textSecondaryLanguage> element can have the English text. And a message can
 * also contain a <phonemeText> element if optional text has been provided to improve the
 * pronunciation of a Text To Speech (TTS) system.
 *
 * <p>
 * MessagesResponse will also optionally contain <interval> elements if they were configured to only be
 * displayed for certain intervals. This is used when a message is defined to be active for a long
 * period of time, but only for certain intervals during that time. So for example, a message couple
 * be configured for a month but it might only be active on Tuesdays and Thursdays from 7am to
 * 9am during that period. There will be a separate interval element for each day of the week. An
 * interval has 4 attributes. The startDay and endDay are integers specifying the day of the week. A
 * 1 is for Monday, 2 for Tuesday, etc. The startTime and endTime are time during the day in
 * seconds that the message is to start or stop. So a value of 3600 corresponds to 1am (3600
 * seconds past midnight).
 *
 * <p>
 * For the agency STL there is an additional optional attribute called sendToBuses that can be
 * included in the <message> element. If it is present and set to true then the message is also
 * intended for display on board buses. If it is not present it should be considered to be false.
 *
 * <p>
 * For the agency "Agence metropolitaine de transport" in Quebec there are two additional
 * elements. The first one is <smsText> and it specifies corresponding text to be provided only via
 * SMS. The second element is <priority> and it is for specifying the priority of the message, 1 being
 * the highest priority and 5 being the lowest.
 *
 * <p>
 * Sample XML output is shown below:
 *
 * <pre>
 * {@code
 * <body>
 *   <route tag="all">
 *     <message id="1700" creator="supervisor" sendToBuses="true" startBoundary="1253232780000" startBoundaryStr="Thu 17:13:00 PDT 2009" endBoundary="1253233200000" endBoundaryStr="Thu Sep 17:20:00 PDT 2009">
 *       <interval startDay="2" startTime="75600" endDay="4" endTime="79200" />
 *       <interval startDay="4" startTime="75600" endDay="4" endTime="79200" />
 *       <text>Thanks for taking transit</text>
 *       <textSecondaryLanguage>Thanks for taking transit, in Spanish</textSecondaryLanguage>
 *       <phonemeText>Thanks for taking traansit</phonemeText>
 *     </message>
 *   </route>
 *   <route tag="bshop">
 *     <message id="1701" creator="supervisor" startBoundary="1253232780000" startBoundaryStr="Thu 17:13:00 PDT 2009" endBoundary="1253233200000" endBoundaryStr="Thu Sep 17:20:00 PDT 2009">
 *       <routeConfiguredForMessage tag="bshop">
 *         <stop tag="1324" title="7th&Main" />
 *         <stop tag="1325" title="9th&Main" />
 *       </routeConfiguredForMessage>
 *       <routeForMessage tag="franklin" />
 *       <text>Detour on Malou St on Thursday</text>
 *       <phonemeText>Detour on Malooo St on Thursday</phonemeText>
 *       <smsText>Detour on Malou on Thursday</smsText>
 *       <priority>3</priority>
 *     </message>
 *   </route>
 * </body>
 * }
 * </pre>
 * @author echatman
 */
@XmlType
public class MessagesResponse extends NextBusResponse {

    private List<RouteMessages> routes = new ArrayList<>();

    @XmlElement(name = "route")
    public List<RouteMessages> getRoutes() {
        return routes;
    }

    @SuppressWarnings("unused")
    private void setRoutes(List<RouteMessages> routes) {
        this.routes = routes;
    }

}
