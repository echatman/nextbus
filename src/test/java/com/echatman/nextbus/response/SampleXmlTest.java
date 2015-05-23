// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.response;

import com.echatman.nextbus.response.agencylist.AgencyListResponse;
import com.echatman.nextbus.response.locations.VehicleLocationsResponse;
import com.echatman.nextbus.response.messages.MessagesResponse;
import com.echatman.nextbus.response.predictions.PredictionsResponse;
import com.echatman.nextbus.response.predictions.PredictionsForMultiStopsResponse;
import com.echatman.nextbus.response.routeconfig.RouteConfigResponse;
import com.echatman.nextbus.response.routelist.RouteListResponse;
import com.echatman.nextbus.response.schedule.ScheduleResponse;
import com.echatman.nextbus.test.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author echatman
 */
@RunWith(Parameterized.class)
public class SampleXmlTest {

    @Parameters(name="{index}: {0} \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { RouteConfigResponse.class, "routeConfig.xml" },
                { RouteListResponse.class, "routeList.xml" },
                { AgencyListResponse.class, "agencyList.xml" },
                { PredictionsResponse.class, "predictions.xml" },
                { PredictionsForMultiStopsResponse.class, "predictionsForMultiStops.xml" },
                { ScheduleResponse.class, "schedule.xml" },
                { MessagesResponse.class, "messages.xml" },
                { VehicleLocationsResponse.class, "vehicleLocations.xml" },
        });
    }

    @Parameter(value=0)
    public Class<? extends NextBusResponse> xmlRootElementClass;
    @Parameter(value=1)
    public String xmlFileName;

    @Test
    public void testNormal() throws Exception {
        NextBusResponse response = TestUtils.testMarshallingFile(xmlRootElementClass, "/sampleXml/" + xmlFileName);
    }

    @Test
    public void testError() throws Exception {
        NextBusResponse response = TestUtils.testMarshallingFile(xmlRootElementClass, "/sampleXml/error.xml");
        assertNotNull(response.getError());
    }

}
