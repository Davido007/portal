package com.travalo.carservice.models;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by DPLICHTA on 5/17/2017.
 */
public class PlaceResponse {
    private List<CarSearchRequest> places;

    public PlaceResponse() {
    }

    @JsonCreator
    public PlaceResponse(@JsonProperty("places") List<CarSearchRequest> places) {
        this.places = places;
    }

    @JsonProperty("places")
    public List<CarSearchRequest> getPlaces() {
        return places;
    }

    public void setPlaces(List<CarSearchRequest> places) {
        this.places = places;
    }
}
