package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarSearchRequest {
    private String originCity;
    private String returnCity;

    public CarSearchRequest() {
    }

    @JsonCreator
    public CarSearchRequest(@JsonProperty("originCity") String originCity, @JsonProperty("returnCity") String returnCity) {
        this.originCity = originCity;
        this.returnCity = returnCity;
    }

    @JsonProperty("originCity")
    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    @JsonProperty("returnCity")
    public String getReturnCity() {
        return returnCity;
    }

    public void setReturnCity(String returnCity) {
        this.returnCity = returnCity;
    }
}
