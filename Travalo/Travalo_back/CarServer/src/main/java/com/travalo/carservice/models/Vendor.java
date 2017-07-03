package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by DPLICHTA on 6/19/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vendor {
    String code;
    String imageUrl;
    String name;
    int tier;


    @JsonCreator
    public Vendor(@JsonProperty("code") String code, @JsonProperty("imageUrl") String imageUrl,
                  @JsonProperty("name")String name, @JsonProperty("tier") int tier) {
        this.code = code;
        this.imageUrl = imageUrl;
        this.name = name;
        this.tier = tier;
    }

    public Vendor() {
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("tier")
    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
