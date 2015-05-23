// (c) 2014 Coverity, Inc. All rights reserved worldwide.
package com.echatman.nextbus.response.predictions;

import com.echatman.nextbus.response.NextBusResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Predictions associated with a stop. Note: the predictions are grouped by
 * direction. For situations where buses on a line have different destinations
 * because some turn back earlier than others, the predictions presented to the
 * user can provide this important piece of information.
 * 
 * @author echatman
 */
@XmlType
public class PredictionsResponse extends NextBusResponse {

    private Predictions predictions;

    @XmlElement
    public Predictions getPredictions() {
        return predictions;
    }

    @SuppressWarnings("unused")
    private void setPredictions(Predictions predictions) {
        this.predictions = predictions;
    }

}
