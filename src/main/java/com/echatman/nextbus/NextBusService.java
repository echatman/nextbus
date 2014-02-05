// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.echatman.nextbus.domain.AgencyList;
import com.echatman.nextbus.domain.PredictionsBody;
import com.echatman.nextbus.domain.RouteConfig;
import com.echatman.nextbus.domain.RouteList;
import com.echatman.nextbus.request.PredictionsRequest;
import com.echatman.nextbus.request.RouteConfigRequest;

/**
 * 
 * @author echatman
 */
public class NextBusService {

    public static final String BASE_URL = "http://webservices.nextbus.com/service/publicXMLFeed";

    /**
     * Obtain a list of available agencies.
     * 
     * @return AgencyList
     * @throws IOException
     * @throws ClientProtocolException
     */
    public AgencyList agencyList() throws ClientProtocolException, IOException {
        return executeRequest(buildAgencyListURI(), AgencyList.class);
    }

    /**
     * @param request
     * @return PredictionsBody
     * @throws ClientProtocolException
     * @throws IOException
     */
    public PredictionsBody predictions(PredictionsRequest request) throws ClientProtocolException, IOException {
        return executeRequest(buildPredictionsURI(request), PredictionsBody.class);
    }

    /**
     * Obtain information about routes for an agency.
     * 
     * @return RouteConfig
     * @throws IOException
     * @throws ClientProtocolException
     */
    public RouteConfig routeConfig(RouteConfigRequest request) throws ClientProtocolException, IOException {
        return executeRequest(buildRouteConfigURI(request), RouteConfig.class);
    }

    /**
     * Obtain a list of routes for an agency. The tag for the agency as obtained
     * from the {@link #agencyList()} command should be used.
     * 
     * @param agencyTag
     *            The tag for the agency as obtained from the
     *            {@link #agencyList()} command
     * @return RouteList
     * @throws IOException
     * @throws ClientProtocolException
     */
    public RouteList routeList(String agencyTag) throws ClientProtocolException, IOException {
        return executeRequest(buildRouteListURI(agencyTag), RouteList.class);
    }

    private <T> T executeRequest(URI uri, Class<T> returnType) throws IOException, ClientProtocolException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            JAXBContext jc = JAXBContext.newInstance(returnType);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement<T> result = unmarshaller.unmarshal(new StreamSource(entity.getContent()), returnType);
            EntityUtils.consume(entity);
            return result.getValue();
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

    private URIBuilder uriBuilder() {
        try {
            return new URIBuilder(BASE_URL);
        } catch (URISyntaxException e) {
            // This means the base URL is somehow invalid. Should never happen.
            throw new RuntimeException(e);
        }
    }

    URI buildAgencyListURI() {
        return toURI(uriBuilder().setParameter("command", "agencyList"));
    }

    URI buildPredictionsURI(PredictionsRequest request) {
        URIBuilder uriBuilder = uriBuilder();
        uriBuilder.setParameter("command", "predictions");
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

    URI buildRouteConfigURI(RouteConfigRequest request) {
        URIBuilder uriBuilder = uriBuilder();
        uriBuilder.setParameter("command", "routeConfig");
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
        return toURI(uriBuilder().setParameter("command", "routeList").setParameter("a", agencyTag));
    }
}
