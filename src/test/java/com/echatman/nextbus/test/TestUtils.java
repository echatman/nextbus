// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 
 * @author echatman
 */
public class TestUtils {

    public static <T> T testSampleXml(Class<T> clazz, String xmlFile) throws JAXBException, IOException, SAXException {
        String expected = getSampleXml(xmlFile);
        T object = unmarshal(clazz, new StreamSource(new StringReader(expected)));
        String actual = marshal(clazz, object);
        assertXmlMatches(expected, actual);
        return object;
    }

    public static <T> void assertXmlMatches(URI uri, Class<T> clazz, T object) throws IOException, SAXException,
            PropertyException, JAXBException {
        String expected = TestUtils.getXml(uri);
        String actual = TestUtils.marshal(clazz, object);
        assertXmlMatches(expected, actual);
    }

    public static void assertXmlMatches(String expected, String actual) throws SAXException, IOException {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
        XMLUnit.setIgnoreWhitespace(true);
        Diff xmlDiff = XMLUnit.compareXML(expected, actual);
        assertTrue(xmlDiff.toString() + System.lineSeparator() + actual, xmlDiff.similar());
    }

    public static <T> String marshal(Class<T> clazz, T object) throws JAXBException, PropertyException {
        StringWriter writer = new StringWriter();
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(new JAXBElement<T>(new QName("body"), clazz, object), writer);
        String actual = writer.toString();
        return actual;
    }

    public static <T> T unmarshal(Class<T> clazz, Source source) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<T> jaxbElement = unmarshaller.unmarshal(source, clazz);
        T object = jaxbElement.getValue();
        return object;
    }

    public static String getSampleXml(String xmlFile) throws IOException {
        InputStream stream = TestUtils.class.getResourceAsStream("/sampleXml/" + xmlFile);
        assertNotNull(stream);
        return IOUtils.toString(stream);
    }

    public static String getXml(URI uri) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            HttpEntity entity = response.getEntity();
            String result = IOUtils.toString(entity.getContent());
            EntityUtils.consume(entity);
            return result;
        } finally {
            response.close();
        }
    }
}
