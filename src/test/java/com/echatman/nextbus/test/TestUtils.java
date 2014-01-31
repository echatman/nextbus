// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.test;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author echatman
 */
public class TestUtils {

    public static <T> T testSampleXml(Class<T> clazz, String xmlFile) throws Exception {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
        XMLUnit.setIgnoreWhitespace(true);
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        InputStream stream = TestUtils.class.getResourceAsStream("/sampleXml/"+xmlFile);
        assertNotNull(stream);
        String expected = IOUtils.toString(stream);
        JAXBElement<T> jaxbElement = unmarshaller.unmarshal(new StreamSource(new StringReader(expected)), clazz);
        T object = jaxbElement.getValue();
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(jaxbElement, writer);
        String actual = writer.toString();
        Diff xmlDiff = XMLUnit.compareXML(expected, actual);
        assertTrue(xmlDiff.toString()+System.lineSeparator()+actual, xmlDiff.similar());
        return object;
    }
}
