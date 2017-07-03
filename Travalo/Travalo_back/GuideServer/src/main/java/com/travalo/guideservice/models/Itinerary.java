package com.travalo.guideservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by DPLICHTA on 6/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Itinerary {
    private String name;
    private String description;
    private ImageOpt imageOpt;

    public Itinerary() {
    }

    @JsonCreator
    public Itinerary(@JsonProperty("name")String name, @JsonProperty("description") String description,
                     @JsonProperty("imageOpt") ImageOpt imageOpt) {
        this.name = name;
        this.description = description;
        this.imageOpt = imageOpt;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("imageOpt")
    public ImageOpt getImageOpt() {
        return imageOpt;
    }

    public void setImageOpt(ImageOpt imageOpt) {
        this.imageOpt = imageOpt;
    }
}
