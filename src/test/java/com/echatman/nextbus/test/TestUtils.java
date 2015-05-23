// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.test;

import com.echatman.nextbus.response.NextBusResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.custommonkey.xmlunit.*;
import org.w3c.dom.Node;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * 
 * @author echatman
 */
public class TestUtils {

    private static int failureIndex = 0;

    /**
     * Verifies that the given class correctly maps every part of the XML returned by the given URI.
     * Fetch the given URI contents, unmarshal and re-marshal it was the given class, and verify that
     * the marshalled XML matches the original XML.
     */
    public static <T extends NextBusResponse> T testMarshallingURI(Class<T> clazz, URI uri) throws Exception {
        String expectedXml = getURIContentsAsString(uri);
        return testMarshalling(clazz, expectedXml, uri.toString());
    }

    public static <T extends NextBusResponse> T testMarshallingFile(Class<T> clazz, String filename) throws Exception {
        String expectedXml = getFileContentsAsString(filename);
        return testMarshalling(clazz, expectedXml, filename);
    }

    private static <T extends NextBusResponse> T testMarshalling(Class<T> clazz, String expectedXml, String sourceLabel) throws Exception {
        T object = unmarshal(clazz, new StreamSource(new StringReader(expectedXml)));
        object.setUrl(sourceLabel);
        String actualXml = marshal(clazz, object);
        assertXmlMatches(expectedXml, actualXml, sourceLabel);
        return object;
    }

    public static void checkForErrors(NextBusResponse response) {
        if (response.getError() != null) {
            fail(response.getUrl() + ": " + response.getError());
        }
    }

    public static void assertXmlMatches(String expected, String actual, String sourceLabel) throws Exception {
        XMLUnit.setNormalizeWhitespace(true);
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setCompareUnmatched(false);
        XMLUnit.setExpandEntityReferences(true);
        XMLUnit.setNormalize(true);
        Diff xmlDiff = new Diff(expected, actual);
        // UUGGGGHHHH I hate the XMLUnit API!
        DifferenceEngine differenceEngine = new CustomDifferenceEngine(xmlDiff);
        Field diffEngineField = xmlDiff.getClass().getDeclaredField("differenceEngine");
        diffEngineField.setAccessible(true);
        diffEngineField.set(xmlDiff, differenceEngine);

        DetailedDiff detailedDiff = new DetailedDiff(xmlDiff);
        if (!xmlDiff.similar()) {
            // Write full XML to temp file
            failureIndex++;
            File expectedFile = new File(FileUtils.getTempDirectory(), "expected"+failureIndex+".xml");
            FileUtils.writeStringToFile(expectedFile, expected);
            File actualFile = new File(FileUtils.getTempDirectory(), "actual"+failureIndex+".xml");
            FileUtils.writeStringToFile(actualFile, actual);
            fail(sourceLabel + System.lineSeparator()
                + describeDifferences(detailedDiff) + System.lineSeparator()
                + "Wrote files: " + System.lineSeparator()
                + expectedFile.getAbsolutePath() + System.lineSeparator()
                + actualFile.getAbsolutePath() + System.lineSeparator());
        }
    }

    private static class CustomDifferenceEngine extends DifferenceEngine {
        public CustomDifferenceEngine(Diff diff) {
            super(diff);
        }
        @Override
        protected void compareNodeList(List controlChildrenArg, List testChildrenArg, int numNodes,
                                       DifferenceListener listener, ElementQualifier elementQualifier) throws DifferenceFoundException {
            List<Node> controlChildren = controlChildrenArg;
            List<Node> testChildren = testChildrenArg;
            // Group child nodes by type and name
            Map<Pair<Short,String>, List<Node>> groupedControlChildren = controlChildren.stream()
                    .collect(groupingBy(node -> Pair.of(node.getNodeType(), node.getNodeName())));
            Map<Pair<Short,String>, List<Node>> groupedTestChildren = testChildren.stream()
                    .collect(groupingBy(node -> Pair.of(node.getNodeType(), node.getNodeName())));
            Set<Pair<Short,String>> allKeys = new HashSet<>(groupedControlChildren.keySet());
            allKeys.addAll(groupedTestChildren.keySet());
            for (Pair<Short,String> key : allKeys) {
                List<Node> controlGroup = groupedControlChildren.getOrDefault(key, Collections.<Node>emptyList());
                List<Node> testGroup = groupedTestChildren.getOrDefault(key, Collections.<Node>emptyList());
                for (int i = 0; i < Math.max(controlGroup.size(), testGroup.size()); i++) {
                    Node controlNode = controlGroup.size() > i ? controlGroup.get(i) : null;
                    Node testNode = testGroup.size() > i ? testGroup.get(i) : null;
                    if (controlNode != null && testNode != null) {
                        compareNode(controlNode, testNode, listener, elementQualifier);
                    } else {
                        // Such an irritating API!
                        try {
                            Method missingNodeMethod = super.getClass().getDeclaredMethod("missingNode", Node.class, Node.class, DifferenceListener.class);
                            missingNodeMethod.setAccessible(true);
                            missingNodeMethod.invoke(this, controlNode, testNode, listener);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        }
    }

    private static String describeDifferences(DetailedDiff detailedDiff) {
        List<Difference> allDifferences = detailedDiff.getAllDifferences();
        List<Difference> differences = allDifferences.stream()
                .filter(diff -> !diff.isRecoverable())
                .collect(toList());

        String result = "Found " + differences.size() + " differences: " + System.lineSeparator();
        for (int i = 0; i < differences.size(); i++) {
            Difference difference = differences.get(i);
            if (i < 2) {
                result += (i+1) + ". " + describeDifference(difference) + System.lineSeparator() + System.lineSeparator();
            } else if (i < 10) {
                result += (i+1) + ". " + difference.toString() + System.lineSeparator();
            } else {
                result += "[... +" + (differences.size() - i) + " more ...]";
                break;
            }
        }
        return result;
    }

    private static String describeDifference(Difference difference) {
        return difference.toString() + System.lineSeparator()
                + "- " + describeNode(difference.getControlNodeDetail()) + System.lineSeparator()
                + "+ " + describeNode(difference.getTestNodeDetail());
    }

    private static String describeNode(NodeDetail nodeDetail) {
        Node node = nodeDetail.getNode();
        if (node == null) {
            return nodeDetail.getValue();
        } else if (!node.hasAttributes() && !node.hasChildNodes()) {
            return node.getNodeName() + " = " + node.getNodeValue();
        } else {
            try {
                return new Transform(node).getResultString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T extends NextBusResponse> String marshal(Class<T> clazz, T object) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(new JAXBElement<>(new QName("body"), clazz, object), writer);
        String actual = writer.toString();
        return actual;
    }

    public static <T extends NextBusResponse> T unmarshal(Class<T> clazz, Source source) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<T> jaxbElement = unmarshaller.unmarshal(source, clazz);
        return jaxbElement.getValue();
    }

    public static String getFileContentsAsString(String filename) throws IOException {
        InputStream stream = TestUtils.class.getResourceAsStream(filename);
        assertNotNull(stream);
        return IOUtils.toString(stream);
    }

    public static String getURIContentsAsString(URI uri) throws IOException {
        // Avoid data limit errors:
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public static InputStream getURIContentsAsStream(URI uri) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        return response.getEntity().getContent();
    }
}
