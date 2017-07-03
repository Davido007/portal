package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by DPLICHTA on 6/19/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarPrice {
    private String roundingPolicy;
    private String roundedValue;
    private String formattedValue;
    private String value;


    public CarPrice() {
    }

    @JsonCreator
    public CarPrice(@JsonProperty("roundingPolicy") String roundingPolicy, @JsonProperty("roundedValue") String roundedValue,
                    @JsonProperty("formattedValue")String formattedValue, @JsonProperty("value")String value) {
        this.roundingPolicy = roundingPolicy;
        this.roundedValue = roundedValue;
        this.formattedValue = formattedValue;
        this.value = value;
    }

    @JsonProperty("roundingPolicy")
    public String getRoundingPolicy() {
        return roundingPolicy;
    }

    public void setRoundingPolicy(String roundingPolicy) {
        this.roundingPolicy = roundingPolicy;
    }

    @JsonProperty("roundedValue")
    public String getRoundedValue() {
        return roundedValue;
    }

    public void setRoundedValue(String roundedValue) {
        this.roundedValue = roundedValue;
    }

    @JsonProperty("formattedValue")
    public String getFormattedValue() {
        return formattedValue;
    }

    public void setFormattedValue(String formattedValue) {
        this.formattedValue = formattedValue;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

