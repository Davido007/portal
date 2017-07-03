package com.travalo.holidayservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * Created by DPLICHTA on 6/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidaysApiResponse {
    private List<HolidaysResult> results;

    public HolidaysApiResponse() {
    }

    @JsonCreator
    public HolidaysApiResponse(@JsonProperty("results") List<HolidaysResult> results) {
        this.results = results;
    }

    @JsonProperty("results")
    public List<HolidaysResult> getResults() {
        return results;
    }

    public void setResults(List<HolidaysResult> results) {
        this.results = results;
    }
}
