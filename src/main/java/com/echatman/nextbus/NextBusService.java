// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus;

import com.echatman.nextbus.request.PredictionsForMultiStopsRequest;
import com.echatman.nextbus.request.PredictionsRequest;
import com.echatman.nextbus.request.RouteConfigRequest;
import com.echatman.nextbus.response.NextBusResponse;
import com.echatman.nextbus.response.agencylist.AgencyListResponse;
import com.echatman.nextbus.response.locations.VehicleLocationsResponse;
import com.echatman.nextbus.response.messages.MessagesResponse;
import com.echatman.nextbus.response.predictions.PredictionsForMultiStopsResponse;
import com.echatman.nextbus.response.predictions.PredictionsResponse;
import com.echatman.nextbus.response.routeconfig.RouteConfigResponse;
import com.echatman.nextbus.response.routelist.RouteListResponse;
import com.echatman.nextbus.response.schedule.ScheduleResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author echatman
 */
public class NextBusService {
    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    public static final String BASE_URL = "http://webservices.nextbus.com/service/publicXMLFeed";
    private static final XmlObjectParser xmlObjectParser = new XmlObjectParser(AgencyListResponse.class,
            VehicleLocationsResponse.class, MessagesResponse.class, PredictionsResponse.class,
            RouteConfigResponse.class, RouteListResponse.class, ScheduleResponse.class);
    private static final HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

    /**
     * Obtain a list of available agencies.
     */
    public AgencyListResponse agencyList() throws  IOException {
        return executeRequest(buildAgencyListURI(), AgencyListResponse.class);
    }

    /**
     * Obtain predictions associated with a stop.
     */
    public PredictionsResponse predictions(PredictionsRequest request) throws  IOException {
        return executeRequest(buildPredictionsURI(request), PredictionsResponse.class);
    }

    /**
     * Obtain predictions associated with multiple stops.
     */
    public PredictionsForMultiStopsResponse predictionsForMultiStops(PredictionsForMultiStopsRequest request)
            throws IOException {
        return executeRequest(buildPredictionsForMultiStopsURI(request), PredictionsForMultiStopsResponse.class);
    }

    /**
     * Obtain the schedule information for a route.
     */
    public ScheduleResponse schedule(String agencyTag, String routeTag) throws IOException {
        return executeRequest(buildScheduleURI(agencyTag, routeTag), ScheduleResponse.class);
    }

    /**
     * Message requests are used to obtain messages that are currently active.
     */
    public MessagesResponse messages(String agencyTag, String...routeTags) throws IOException {
        return executeRequest(buildMessagesURI(agencyTag, routeTags), MessagesResponse.class);
    }

    /**
     * Vehicle locations are used to draw vehicles on a map or to simply store them in a database. The
     * vehicle locations should not be polled more than once every 10 seconds.
     *
     * @param agencyTag The tag for the agency as obtained from the agencyList command
     * @param routeTag The tag for the route as obtained from the routeList command
     * @param lastTime the last time that was returned by the vehicleLocations command.
     *                 The time is in milliseconds since the 1970 epoch time.
     *                 If you specify a time of 0, then data for the last 15 minutes is provided.
     * @return a list of vehicle locations that have changed since the last time the vehicleLocations command was used.
     * @throws IOException
     */
    public VehicleLocationsResponse vehicleLocations(String agencyTag, String routeTag, long lastTime) throws IOException {
        return executeRequest(buildVehicleLocationsURI(agencyTag, routeTag, lastTime), VehicleLocationsResponse.class);
    }

    /**
     * Obtain information about routes for an agency.
     * 
     * @return RouteConfigResponse
     * @throws IOException
     * @throws ClientProtocolException
     */
    public RouteConfigResponse routeConfig(RouteConfigRequest request) throws ClientProtocolException, IOException {
        return executeRequest(buildRouteConfigURI(request), RouteConfigResponse.class);
    }

    /**
     * Obtain a list of routes for an agency. The tag for the agency as obtained
     * from the {@link #agencyList()} command should be used.
     * 
     * @param agencyTag
     *            The tag for the agency as obtained from the
     *            {@link #agencyList()} command
     * @return RouteListResponse
     * @throws IOException
     * @throws ClientProtocolException
     */
    public RouteListResponse routeList(String agencyTag) throws ClientProtocolException, IOException {
        return executeRequest(buildRouteListURI(agencyTag), RouteListResponse.class);
    }

    private <T extends NextBusResponse> T executeRequest(URI uri, Class<T> returnType) throws IOException, ClientProtocolException {
        GenericUrl genericUrl = new GenericUrl(uri);
        logger.fine("Fetching URL: " + genericUrl);
        HttpRequest request = requestFactory.buildGetRequest(genericUrl).setParser(xmlObjectParser);
        T responseObject = request.execute().parseAs(returnType);
        responseObject.setUrl(uri.toString());
        return responseObject;
    }

    private URI toURI(GenericUrl uriBuilder) {
        return uriBuilder.toURI();
    }

    private GenericUrl uriBuilder(String command) {
        return new GenericUrl(BASE_URL).set("command", command);
    }

    URI buildAgencyListURI() {
        return toURI(uriBuilder("agencyList"));
    }

    URI buildPredictionsURI(PredictionsRequest request) {
        GenericUrl uriBuilder = uriBuilder("predictions");
        uriBuilder.set("a", request.getAgencyTag());
        if (request.getRouteTag() != null) {
            uriBuilder.set("r", request.getRouteTag());
        }
        if (request.getStopId() != null) {
            uriBuilder.set("stopId", request.getStopId());
        }
        if (request.getStopTag() != null) {
            uriBuilder.set("s", request.getStopTag());
        }
        if (request.isUseShortTitles()) {
            uriBuilder.set("useShortTitles", "true");
        }
        return toURI(uriBuilder);
    }

    URI buildPredictionsForMultiStopsURI(PredictionsForMultiStopsRequest request) {
        GenericUrl uriBuilder = uriBuilder("predictionsForMultiStops");
        uriBuilder.set("a", request.getAgencyTag());
        uriBuilder.set("stops", request.getStops());
        if (request.isUseShortTitles()) {
            uriBuilder.set("useShortTitles", "true");
        }
        return toURI(uriBuilder);
    }

    URI buildRouteConfigURI(RouteConfigRequest request) {
        GenericUrl uriBuilder = uriBuilder("routeConfig");
        uriBuilder.set("a", request.getAgencyTag());
        if (request.getRouteTag() != null) {
            uriBuilder.set("r", request.getRouteTag());
        }
        if (request.isTerse()) {
            uriBuilder.set("terse", "true");
        }
        if (request.isVerbose()) {
            uriBuilder.set("verbose", "true");
        }
        return toURI(uriBuilder);
    }

    URI buildRouteListURI(String agencyTag) {
        return toURI(uriBuilder("routeList").set("a", agencyTag));
    }

    URI buildVehicleLocationsURI(String agencyTag, String routeTag, long lastTime) {
        return toURI(uriBuilder("vehicleLocations")
                .set("a", agencyTag)
                .set("r", routeTag)
                .set("t", Long.toString(lastTime))
        );
    }

    URI buildMessagesURI(String agencyTag, String...routeTags) {
        GenericUrl uriBuilder = uriBuilder("messages")
                .set("a", agencyTag);
        if (routeTags.length > 0) {
            uriBuilder.set("r", Arrays.asList(routeTags));
        }
        return toURI(uriBuilder);
    }

    URI buildScheduleURI(String agencyTag, String routeTag) {
        return toURI(uriBuilder("schedule")
            .set("a", agencyTag)
            .set("r", routeTag));
    }

}
