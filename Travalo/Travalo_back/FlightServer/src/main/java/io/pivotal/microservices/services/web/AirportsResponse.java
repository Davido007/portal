package io.pivotal.microservices.services.web;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class AirportsResponse {
    private List<Airport> airports;

    public AirportsResponse() {
    }

    @JsonCreator
    public AirportsResponse(@JsonProperty("airports") List<Airport> airports) {
        this.airports = airports;
    }

    @JsonProperty("airports")
    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }
}
