// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.echatman.nextbus.test.TestUtils;

/**
 *
 * @author echatman
 */
@RunWith(Parameterized.class)
public class SampleXmlTest {

    @Parameters(name="{index}: {0} \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { RouteConfig.class, "routeConfig.xml" },
                { RouteList.class, "routeList.xml" },
                { AgencyList.class, "agencyList.xml" },
                { PredictionsBody.class, "predictions.xml" },
                { PredictionsForMultiStops.class, "predictionsForMultiStops.xml" },
                { Schedule.class, "schedule.xml" },
                { Messages.class, "messages.xml" },
                { VehicleLocations.class, "vehicleLocations.xml" },
        });
    }

    @Parameter(value=0)
    public Class<?> xmlRootElementClass;
    @Parameter(value=1)
    public String xmlFileName;

    @Test
    public void testNormal() throws Exception {
        TestUtils.testMarshalling(xmlRootElementClass, TestUtils.getFileContentsAsString("/sampleXml/"+xmlFileName));
    }

    @Test
    public void testError() throws Exception {
        TestUtils.testMarshalling(xmlRootElementClass, TestUtils.getFileContentsAsString("/sampleXml/error.xml"));
    }

}
