package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/19/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarFare {
    private String currencyCode;
    private CarPrice total;


    public CarFare() {
    }

    @JsonCreator
    public CarFare(@JsonProperty("currencyCode") String currencyCode, @JsonProperty("total") CarPrice total)
    {
        this.currencyCode = currencyCode;
        this.total = total;
    }

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("total")
    public CarPrice getTotal() {
        return total;
    }

    public void setTotal(CarPrice total) {
        this.total = total;
    }
}

