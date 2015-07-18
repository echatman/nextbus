package com.echatman.nextbus;

import com.google.api.client.util.ObjectParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * @author echatman
 */
public class XmlObjectParser implements ObjectParser {
    JAXBContext jc;

    public XmlObjectParser(Class<?>...classesToBind) {
        try {
            jc = JAXBContext.newInstance(classesToBind);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private Unmarshaller getUnmarshaller() {
        try {
            return jc.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T parseAndClose(InputStream in, Charset charset, Class<T> dataClass) throws IOException {
        return parseAndClose(new StreamSource(in), dataClass);
    }

    @Override
    public <T> T parseAndClose(Reader reader, Class<T> dataClass) throws IOException {
        return parseAndClose(new StreamSource(reader), dataClass);
    }

    public <T> T parseAndClose(Source source, Class<T> dataClass) throws IOException {
        try {
            JAXBElement<T> result = getUnmarshaller().unmarshal(source, dataClass);
            return result.getValue();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object parseAndClose(InputStream in, Charset charset, Type dataType) throws IOException {
        throw new UnsupportedOperationException("Cannot parse Type " + dataType);
    }

    @Override
    public Object parseAndClose(Reader reader, Type dataType) throws IOException {
        throw new UnsupportedOperationException("Cannot parse Type " + dataType);
    }
}
