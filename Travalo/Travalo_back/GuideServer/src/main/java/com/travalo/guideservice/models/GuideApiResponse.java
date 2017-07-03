package com.travalo.guideservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by DPLICHTA on 6/21/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuideApiResponse {
    private List<Itinerary> topItins;

    public GuideApiResponse() {
    }

    @JsonCreator
    public GuideApiResponse(@JsonProperty("topItins")  List<Itinerary> topItins) {
        this.topItins = topItins;
    }

    @JsonProperty("topItins")
    public List<Itinerary> getTopItins() {
        return topItins;
    }

    public void setTopItins(List<Itinerary> topItins) {
        this.topItins = topItins;
    }
}
