package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by DPLICHTA on 6/19/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarClassification {
    String code;
    String name;

    public CarClassification() {
    }

    @JsonCreator
    public CarClassification(@JsonProperty("code") String code, @JsonProperty("name") String name) {
        this.code = code;
        this.name = name;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}