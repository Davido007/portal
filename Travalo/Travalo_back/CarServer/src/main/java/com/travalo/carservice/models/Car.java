package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/19/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Car {
    private CarClassification classification;
    private String transmission;
    private Capacity passengerCapacity;
    private boolean hasAirConditioning;
    private String exampleMakeModel;
    private Capacity luggageCapacity;
    private String imageURL;
    private String description;

    public Car() {
    }

    @JsonCreator
    public Car(@JsonProperty("classification") CarClassification classification, @JsonProperty("transmission") String transmission,
               @JsonProperty("passengerCapacity") Capacity passengerCapacity, @JsonProperty("hasAirConditioning") boolean hasAirConditioning,
               @JsonProperty("exampleMakeModel") String exampleMakeModel, @JsonProperty("luggageCapacity") Capacity luggageCapacity,
               @JsonProperty("imageURL") String imageURL, @JsonProperty("description") String description) {
        this.classification = classification;
        this.transmission = transmission;
        this.passengerCapacity = passengerCapacity;
        this.hasAirConditioning = hasAirConditioning;
        this.exampleMakeModel = exampleMakeModel;
        this.luggageCapacity = luggageCapacity;
        this.imageURL = imageURL;
        this.description = description;
    }

    @JsonProperty("classification")
    public CarClassification getClassification() {
        return classification;
    }

    public void setClassification(CarClassification classification) {
        this.classification = classification;
    }

    @JsonProperty("transmission")
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @JsonProperty("passengerCapacity")
    public Capacity getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Capacity passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @JsonProperty("hasAirConditioning")
    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    @JsonProperty("exampleMakeModel")
    public String getExampleMakeModel() {
        return exampleMakeModel;
    }

    public void setExampleMakeModel(String exampleMakeModel) {
        this.exampleMakeModel = exampleMakeModel;
    }

    @JsonProperty("luggageCapacity")
    public Capacity getLuggageCapacity() {
        return luggageCapacity;
    }

    public void setLuggageCapacity(Capacity luggageCapacity) {
        this.luggageCapacity = luggageCapacity;
    }

    @JsonProperty("imageURL")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

