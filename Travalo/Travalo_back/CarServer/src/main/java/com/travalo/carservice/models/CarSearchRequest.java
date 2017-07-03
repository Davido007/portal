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
    private String originCountry;
    private String returnCity;
    private String returnCountry;
    private String pickUpDate;
    private String dropOffDate;

    public CarSearchRequest() {
    }

    @JsonCreator
    public CarSearchRequest(@JsonProperty("originCity") String originCity, @JsonProperty("returnCity") String returnCity,
                            @JsonProperty("pickUpDate") String pickUpDate, @JsonProperty("dropOffDate") String dropOffDate,
                            @JsonProperty("originCountry") String originCountry, @JsonProperty("returnCountry") String returnCountry) {
        this.originCity = originCity;
        this.returnCity = returnCity;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.originCountry = originCountry;
        this.returnCountry = returnCountry;
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

    @JsonProperty("pickUpDate")
    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    @JsonProperty("dropOffDate")
    public String getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(String dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    @JsonProperty("originCountry")
    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @JsonProperty("returnCountry")
    public String getReturnCountry() {
        return returnCountry;
    }

    public void setReturnCountry(String returnCountry) {
        this.returnCountry = returnCountry;
    }
}
