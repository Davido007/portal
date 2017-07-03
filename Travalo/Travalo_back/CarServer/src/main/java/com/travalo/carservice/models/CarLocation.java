package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/19/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarLocation {
    private String address;
    private String country;

    public CarLocation() {
    }

    @JsonCreator
    public CarLocation(@JsonProperty("address") String address, @JsonProperty("country") String country)
    {
        this.address = address;
        this.country = country;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

