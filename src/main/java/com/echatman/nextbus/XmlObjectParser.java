package com.echatman.nextbus;

import com.echatman.nextbus.response.NextBusResponse;
import com.google.api.client.util.ObjectParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
    Unmarshaller unmarshaller;

    public XmlObjectParser(Class<? extends NextBusResponse>...classesToBind) {
        try {
            jc = JAXBContext.newInstance(classesToBind);
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T parseAndClose(InputStream in, Charset charset, Class<T> dataClass) throws IOException {
        try {
            JAXBElement<T> result = unmarshaller.unmarshal(new StreamSource(in), dataClass);
            return result.getValue();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object parseAndClose(InputStream in, Charset charset, Type dataType) throws IOException {
        return null;
    }

    @Override
    public <T> T parseAndClose(Reader reader, Class<T> dataClass) throws IOException {
        return null;
    }

    @Override
    public Object parseAndClose(Reader reader, Type dataType) throws IOException {
        return null;
    }
}
