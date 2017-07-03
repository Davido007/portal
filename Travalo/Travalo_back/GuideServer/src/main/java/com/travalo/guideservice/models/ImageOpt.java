package com.travalo.guideservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/21/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageOpt {
    private String name;
    private Long id;

    public ImageOpt() {
    }

    @JsonCreator
    public ImageOpt(@JsonProperty("name") String name, @JsonProperty("id")Long id) {
        this.name = name;
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
