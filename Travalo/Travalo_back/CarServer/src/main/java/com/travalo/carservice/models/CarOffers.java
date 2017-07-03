package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/19/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarOffers {
    private CarFare fare;
    private String piid;
    private CarLocation pickUpLocation;
    private CarLocation dropOffLocation;
    private Car vehicle;
    private Vendor vendor;


    public CarOffers() {
    }

    @JsonCreator
    public CarOffers(@JsonProperty("piid") String piid, @JsonProperty("fare") CarFare fare,
                     @JsonProperty("pickUpLocation") CarLocation pickUpLocation,
                     @JsonProperty("dropOffLocation") CarLocation dropOffLocation,
                     @JsonProperty("vehicle") Car vehicle, @JsonProperty("vendor") Vendor vendor) {
        this.piid = piid;
        this.fare = fare;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.vehicle = vehicle;
        this.vendor = vendor;
    }


    @JsonProperty("piid")
    public String getPiid() {
        return piid;
    }

    public void setPiid(String piid) {
        this.piid = piid;
    }

    @JsonProperty("fare")
    public CarFare getFare() {
        return fare;
    }

    public void setFare(CarFare fare) {
        this.fare = fare;
    }

    @JsonProperty("pickUpLocation")
    public CarLocation getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(CarLocation pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    @JsonProperty("dropOffLocation")
    public CarLocation getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(CarLocation dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    @JsonProperty("vehicle")
    public Car getVehicle() {
        return vehicle;
    }

    public void setVehicle(Car vehicle) {
        this.vehicle = vehicle;
    }

    @JsonProperty("vendor")
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}

