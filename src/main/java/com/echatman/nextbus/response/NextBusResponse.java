package com.echatman.nextbus.response;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author echatman
 */
@XmlRootElement(name = "body")
public abstract class NextBusResponse {
    private String copyright;
    private Error error;
    private String url;

    @XmlAttribute
    public String getCopyright() {
        return copyright;
    }

    @XmlElement(name = "Error")
    public Error getError() {
        return error;
    }

    @SuppressWarnings("unused")
    private void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @SuppressWarnings("unused")
    private void setError(Error error) {
        this.error = error;
    }

    @XmlTransient
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
