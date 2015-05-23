// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus;

import com.echatman.nextbus.request.PredictionsForMultiStopsRequest;
import com.echatman.nextbus.request.PredictionsRequest;
import com.echatman.nextbus.request.RouteConfigRequest;
import com.echatman.nextbus.response.NextBusResponse;
import com.echatman.nextbus.response.agencylist.AgencyListResponse;
import com.echatman.nextbus.response.locations.VehicleLocationsResponse;
import com.echatman.nextbus.response.messages.MessagesResponse;
import com.echatman.nextbus.response.predictions.PredictionsResponse;
import com.echatman.nextbus.response.predictions.PredictionsForMultiStopsResponse;
import com.echatman.nextbus.response.routeconfig.RouteConfigResponse;
import com.echatman.nextbus.response.routelist.RouteListResponse;
import com.echatman.nextbus.response.schedule.ScheduleResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author echatman
 */
public class NextBusService {

    public static final String BASE_URL = "http://webservices.nextbus.com/service/publicXMLFeed";

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
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            JAXBContext jc = JAXBContext.newInstance(returnType);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement<T> result = unmarshaller.unmarshal(new StreamSource(entity.getContent()), returnType);
            EntityUtils.consume(entity);
            T responseObject = result.getValue();
            responseObject.setUrl(uri.toString());
            return responseObject;
        } catch (JAXBException e) {
            // This indicates a bug in this library. Should hopefully never
            // happen.
            throw new RuntimeException(e);
        } finally {
            response.close();
        }
    }

    private URI toURI(URIBuilder uriBuilder) {
        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            // This indicates a bug in this library. Should hopefully never
            // happen.
            throw new RuntimeException(e);
        }
    }

    private URIBuilder uriBuilder(String command) {
        try {
            return new URIBuilder(BASE_URL).setParameter("command", command);
        } catch (URISyntaxException e) {
            // This means the base URL is somehow invalid. Should never happen.
            throw new RuntimeException(e);
        }
    }

    URI buildAgencyListURI() {
        return toURI(uriBuilder("agencyList"));
    }

    URI buildPredictionsURI(PredictionsRequest request) {
        URIBuilder uriBuilder = uriBuilder("predictions");
        uriBuilder.setParameter("a", request.getAgencyTag());
        if (request.getRouteTag() != null) {
            uriBuilder.setParameter("r", request.getRouteTag());
        }
        if (request.getStopId() != null) {
            uriBuilder.setParameter("stopId", request.getStopId());
        }
        if (request.getStopTag() != null) {
            uriBuilder.setParameter("s", request.getStopTag());
        }
        if (request.isUseShortTitles()) {
            uriBuilder.setParameter("useShortTitles", "true");
        }
        return toURI(uriBuilder);
    }

    URI buildPredictionsForMultiStopsURI(PredictionsForMultiStopsRequest request) {
        URIBuilder uriBuilder = uriBuilder("predictionsForMultiStops");
        uriBuilder.setParameter("a", request.getAgencyTag());
        for (String stop : request.getStops()) {
            uriBuilder.addParameter("stops", stop);
        }
        if (request.isUseShortTitles()) {
            uriBuilder.setParameter("useShortTitles", "true");
        }
        return toURI(uriBuilder);
    }

    URI buildRouteConfigURI(RouteConfigRequest request) {
        URIBuilder uriBuilder = uriBuilder("routeConfig");
        uriBuilder.setParameter("a", request.getAgencyTag());
        if (request.getRouteTag() != null) {
            uriBuilder.setParameter("r", request.getRouteTag());
        }
        if (request.isTerse()) {
            uriBuilder.setParameter("terse", "true");
        }
        if (request.isVerbose()) {
            uriBuilder.setParameter("verbose", "true");
        }
        return toURI(uriBuilder);
    }

    URI buildRouteListURI(String agencyTag) {
        return toURI(uriBuilder("routeList").setParameter("a", agencyTag));
    }

    URI buildVehicleLocationsURI(String agencyTag, String routeTag, long lastTime) {
        return toURI(uriBuilder("vehicleLocations")
                .setParameter("a", agencyTag)
                .setParameter("r", routeTag)
                .setParameter("t", Long.toString(lastTime))
        );
    }

    URI buildMessagesURI(String agencyTag, String...routeTags) {
        URIBuilder uriBuilder = uriBuilder("messages")
                .setParameter("a", agencyTag);
        for (String routeTag : routeTags) {
            uriBuilder.addParameter("r", routeTag);
        }
        return toURI(uriBuilder);
    }

    URI buildScheduleURI(String agencyTag, String routeTag) {
        return toURI(uriBuilder("schedule")
            .setParameter("a", agencyTag)
            .setParameter("r", routeTag));
    }

}
