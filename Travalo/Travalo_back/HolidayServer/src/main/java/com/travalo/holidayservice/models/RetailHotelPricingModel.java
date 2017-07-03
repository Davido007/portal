package com.travalo.holidayservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/21/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailHotelPricingModel {

    private String price;
    private String priceFormatted;
    private String packageSavings;
    private String packageSavingsFormatted;
    private String referencePrice;
    private String referencePriceFormatted;
    private String pricePerNight;
    private String pricePerNightFormatted;
    private String referencePricePerNight;
    private String referencePricePerNightFormatted;

    public RetailHotelPricingModel() {
    }

    @JsonCreator
    public RetailHotelPricingModel(@JsonProperty("price") String price, @JsonProperty("priceFormatted") String priceFormatted,
                                   @JsonProperty("packageSavings") String packageSavings, @JsonProperty("packageSavingsFormatted") String packageSavingsFormatted,
                                   @JsonProperty("referencePrice") String referencePrice, @JsonProperty("referencePriceFormatted") String referencePriceFormatted,
                                   @JsonProperty("pricePerNight") String pricePerNight, @JsonProperty("pricePerNightFormatted") String pricePerNightFormatted,
                                   @JsonProperty("referencePricePerNight") String referencePricePerNight, @JsonProperty("referencePricePerNightFormatted") String referencePricePerNightFormatted) {
        this.price = price;
        this.priceFormatted = priceFormatted;
        this.packageSavings = packageSavings;
        this.packageSavingsFormatted = packageSavingsFormatted;
        this.referencePrice = referencePrice;
        this.referencePriceFormatted = referencePriceFormatted;
        this.pricePerNight = pricePerNight;
        this.pricePerNightFormatted = pricePerNightFormatted;
        this.referencePricePerNight = referencePricePerNight;
        this.referencePricePerNightFormatted = referencePricePerNightFormatted;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("priceFormatted")
    public String getPriceFormatted() {
        return priceFormatted;
    }

    public void setPriceFormatted(String priceFormatted) {
        this.priceFormatted = priceFormatted;
    }

    @JsonProperty("packageSavings")
    public String getPackageSavings() {
        return packageSavings;
    }

    public void setPackageSavings(String packageSavings) {
        this.packageSavings = packageSavings;
    }

    @JsonProperty("packageSavingsFormatted")
    public String getPackageSavingsFormatted() {
        return packageSavingsFormatted;
    }

    public void setPackageSavingsFormatted(String packageSavingsFormatted) {
        this.packageSavingsFormatted = packageSavingsFormatted;
    }

    @JsonProperty("referencePrice")
    public String getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
        this.referencePrice = referencePrice;
    }

    @JsonProperty("referencePriceFormatted")
    public String getReferencePriceFormatted() {
        return referencePriceFormatted;
    }

    public void setReferencePriceFormatted(String referencePriceFormatted) {
        this.referencePriceFormatted = referencePriceFormatted;
    }

    @JsonProperty("pricePerNight")
    public String getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @JsonProperty("pricePerNightFormatted")
    public String getPricePerNightFormatted() {
        return pricePerNightFormatted;
    }


    public void setPricePerNightFormatted(String pricePerNightFormatted) {
        this.pricePerNightFormatted = pricePerNightFormatted;
    }

    @JsonProperty("referencePricePerNight")
    public String getReferencePricePerNight() {
        return referencePricePerNight;
    }

    public void setReferencePricePerNight(String referencePricePerNight) {
        this.referencePricePerNight = referencePricePerNight;
    }

    @JsonProperty("referencePricePerNightFormatted")
    public String getReferencePricePerNightFormatted() {
        return referencePricePerNightFormatted;
    }

    public void setReferencePricePerNightFormatted(String referencePricePerNightFormatted) {
        this.referencePricePerNightFormatted = referencePricePerNightFormatted;
    }
}
