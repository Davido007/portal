package com.travalo.holidayservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/21/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Holidays {
    private String hotelName;
    private String neighborhoodOrCityName;
    private String hotelDescription;
    private String bigThumbnailURL;
    private String priceFormatted;
    private String hotelStarRating;

    public Holidays() {
    }

    @JsonCreator
    public Holidays(@JsonProperty("hotelName") String hotelName, @JsonProperty("neighborhoodOrCityName") String neighborhoodOrCityName,
                    @JsonProperty("hotelDescription") String hotelDescription, @JsonProperty("bigThumbnailURL") String bigThumbnailURL,
                    @JsonProperty("priceFormatted") String priceFormatted, @JsonProperty("hotelStarRating") String hotelStarRating) {
        this.hotelName = hotelName;
        this.neighborhoodOrCityName = neighborhoodOrCityName;
        this.hotelDescription = hotelDescription;
        this.bigThumbnailURL = bigThumbnailURL;
        this.priceFormatted = priceFormatted;
        this.hotelStarRating = hotelStarRating;
    }

    @JsonProperty("hotelName")
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @JsonProperty("neighborhoodOrCityName")
    public String getNeighborhoodOrCityName() {
        return neighborhoodOrCityName;
    }

    public void setNeighborhoodOrCityName(String neighborhoodOrCityName) {
        this.neighborhoodOrCityName = neighborhoodOrCityName;
    }

    @JsonProperty("hotelDescription")
    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    @JsonProperty("bigThumbnailURL")
    public String getBigThumbnailURL() {
        return bigThumbnailURL;
    }

    public void setBigThumbnailURL(String bigThumbnailURL) {
        this.bigThumbnailURL = bigThumbnailURL;
    }

    @JsonProperty("priceFormatted")
    public String getPriceFormatted() {
        return priceFormatted;
    }

    public void setPriceFormatted(String priceFormatted) {
        this.priceFormatted = priceFormatted;
    }

    @JsonProperty("hotelStarRating")
    public String getHotelStarRating() {
        return hotelStarRating;
    }

    public void setHotelStarRating(String hotelStarRating) {
        this.hotelStarRating = hotelStarRating;
    }
}
