package io.pivotal.microservices.services.web;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by DPLICHTA on 5/17/2017.
 */
public class FlightResponse {
    private List<Travel> travels;

    public FlightResponse() {
    }

    @JsonCreator
    public FlightResponse(@JsonProperty("travels") List<Travel> travels) {
        this.travels = travels;
    }

    @JsonProperty("travels")
    public List<Travel> getTravels() {
        return travels;
    }

    public void setTravels(List<Travel> travels) {
        this.travels = travels;
    }
}
