package com.echatman.nextbus.response.routeconfig;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

/**
 * @author echatman
 */
@XmlType
public class StopRef {

    private StopConfig stop;

    @XmlAttribute(name = "tag")
    @XmlIDREF
    public StopConfig getStop() {
        return stop;
    }

    @SuppressWarnings("unused")
    private void setStop(StopConfig stop) {
        this.stop = stop;
    }
}
