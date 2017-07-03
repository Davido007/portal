package com.travalo.holidayservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/21/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidaysResult {

    private RetailHotelInfoModel retailHotelInfoModel;
    private RetailHotelPricingModel retailHotelPricingModel;
    private String hotelStarRating;
    private String isFreeCancel;


    public HolidaysResult() {
    }


    @JsonCreator
    public HolidaysResult(@JsonProperty("retailHotelInfoModel") RetailHotelInfoModel retailHotelInfoModel, @JsonProperty("retailHotelPricingModel") RetailHotelPricingModel retailHotelPricingModel,
                          @JsonProperty("hotelStarRating") String hotelStarRating, @JsonProperty("isFreeCancel") String isFreeCancel) {
        this.retailHotelInfoModel = retailHotelInfoModel;
        this.retailHotelPricingModel = retailHotelPricingModel;
        this.hotelStarRating = hotelStarRating;
        this.isFreeCancel = isFreeCancel;
    }

    @JsonProperty("retailHotelInfoModel")
    public RetailHotelInfoModel getRetailHotelInfoModel() {
        return retailHotelInfoModel;
    }

    public void setRetailHotelInfoModel(RetailHotelInfoModel retailHotelInfoModel) {
        this.retailHotelInfoModel = retailHotelInfoModel;
    }

    @JsonProperty("retailHotelPricingModel")
    public RetailHotelPricingModel getRetailHotelPricingModel() {
        return retailHotelPricingModel;
    }

    public void setRetailHotelPricingModel(RetailHotelPricingModel retailHotelPricingModel) {
        this.retailHotelPricingModel = retailHotelPricingModel;
    }

    @JsonProperty("hotelStarRating")
    public String getHotelStarRating() {
        return hotelStarRating;
    }

    public void setHotelStarRating(String hotelStarRating) {
        this.hotelStarRating = hotelStarRating;
    }

    @JsonProperty("isFreeCancel")
    public String getIsFreeCancel() {
        return isFreeCancel;
    }

    public void setIsFreeCancel(String isFreeCancel) {
        this.isFreeCancel = isFreeCancel;
    }
}
