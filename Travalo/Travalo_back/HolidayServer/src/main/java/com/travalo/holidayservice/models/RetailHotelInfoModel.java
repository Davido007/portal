package com.travalo.holidayservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/21/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailHotelInfoModel {
    String searchDestination;
    String hotelName;
    String neighborhoodOrCityName;
    String hotelDescription;
    String thumbnailURL;
    String largeThumbnailURL;
    String bigThumbnailURL;
    String hasAvailableOffer;
    String vrNumberOfBedrooms;
    String vrNumberOfBathrooms;
    String vrNumberOfOccupants;

    public RetailHotelInfoModel() {
    }

    @JsonCreator
    public RetailHotelInfoModel(@JsonProperty("searchDestination") String searchDestination, @JsonProperty("hotelName") String hotelName,
                                @JsonProperty("neighborhoodOrCityName") String neighborhoodOrCityName, @JsonProperty("hotelDescription") String hotelDescription,
                                @JsonProperty("thumbnailURL") String thumbnailURL, @JsonProperty("largeThumbnailURL") String largeThumbnailURL,
                                @JsonProperty("bigThumbnailURL") String bigThumbnailURL, @JsonProperty("hasAvailableOffer") String hasAvailableOffer,
                                @JsonProperty("vrNumberOfBedrooms") String vrNumberOfBedrooms, @JsonProperty("vrNumberOfBathrooms") String vrNumberOfBathrooms,
                                @JsonProperty("vrNumberOfOccupants") String vrNumberOfOccupants) {
        this.searchDestination = searchDestination;
        this.hotelName = hotelName;
        this.neighborhoodOrCityName = neighborhoodOrCityName;
        this.hotelDescription = hotelDescription;
        this.thumbnailURL = thumbnailURL;
        this.largeThumbnailURL = largeThumbnailURL;
        this.bigThumbnailURL = bigThumbnailURL;
        this.hasAvailableOffer = hasAvailableOffer;
        this.vrNumberOfBedrooms = vrNumberOfBedrooms;
        this.vrNumberOfBathrooms = vrNumberOfBathrooms;
        this.vrNumberOfOccupants = vrNumberOfOccupants;
    }

    @JsonProperty("searchDestination")
    public String getSearchDestination() {
        return searchDestination;
    }

    public void setSearchDestination(String searchDestination) {
        this.searchDestination = searchDestination;
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

    @JsonProperty("thumbnailURL")
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @JsonProperty("largeThumbnailURL")
    public String getLargeThumbnailURL() {
        return largeThumbnailURL;
    }

    public void setLargeThumbnailURL(String largeThumbnailURL) {
        this.largeThumbnailURL = largeThumbnailURL;
    }

    @JsonProperty("bigThumbnailURL")
    public String getBigThumbnailURL() {
        return bigThumbnailURL;
    }

    public void setBigThumbnailURL(String bigThumbnailURL) {
        this.bigThumbnailURL = bigThumbnailURL;
    }

    @JsonProperty("hasAvailableOffer")
    public String getHasAvailableOffer() {
        return hasAvailableOffer;
    }

    public void setHasAvailableOffer(String hasAvailableOffer) {
        this.hasAvailableOffer = hasAvailableOffer;
    }

    @JsonProperty("vrNumberOfBedrooms")
    public String getVrNumberOfBedrooms() {
        return vrNumberOfBedrooms;
    }

    public void setVrNumberOfBedrooms(String vrNumberOfBedrooms) {
        this.vrNumberOfBedrooms = vrNumberOfBedrooms;
    }

    @JsonProperty("vrNumberOfBathrooms")
    public String getVrNumberOfBathrooms() {
        return vrNumberOfBathrooms;
    }

    public void setVrNumberOfBathrooms(String vrNumberOfBathrooms) {
        this.vrNumberOfBathrooms = vrNumberOfBathrooms;
    }

    @JsonProperty("vrNumberOfOccupants")
    public String getVrNumberOfOccupants() {
        return vrNumberOfOccupants;
    }

    public void setVrNumberOfOccupants(String vrNumberOfOccupants) {
        this.vrNumberOfOccupants = vrNumberOfOccupants;
    }
}
