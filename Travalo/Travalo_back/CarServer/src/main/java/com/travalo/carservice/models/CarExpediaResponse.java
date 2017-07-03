package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by DPLICHTA on 6/19/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarExpediaResponse {
    private List<CarOffers> offers;

    public CarExpediaResponse() {
    }

    @JsonCreator
    public CarExpediaResponse(@JsonProperty("offers") List<CarOffers> offers) {
        this.offers = offers;
    }


    @JsonProperty("offers")
    public List<CarOffers> getOffers() {
        return offers;
    }

    public void setOffers(List<CarOffers> offers) {
        this.offers = offers;
    }
}
