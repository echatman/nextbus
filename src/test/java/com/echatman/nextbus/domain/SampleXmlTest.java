// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.domain;

import org.junit.Test;

import static com.echatman.nextbus.test.TestUtils.testSampleXml;

/**
 *
 * @author echatman
 */
public class SampleXmlTest {

    @Test
    public void routeConfig() throws Exception {
        testSampleXml(RouteConfig.class, "routeConfig.xml");
    }

    @Test
    public void routeList() throws Exception {
        testSampleXml(RouteList.class, "routeList.xml");
    }

    @Test
    public void agencyList() throws Exception {
        testSampleXml(AgencyList.class, "agencyList.xml");
    }

    @Test
    public void error() throws Exception {
        testSampleXml(RouteConfig.class, "error.xml");
        testSampleXml(RouteList.class, "error.xml");
        testSampleXml(AgencyList.class, "error.xml");
    }
}
