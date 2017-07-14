package friendsService.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {
    private Geometry geometry;
    private HotelProperties properties;

    public Hotel() {
    }

    @JsonCreator
    public Hotel(@JsonProperty("geometry") Geometry geometry, @JsonProperty("properties")HotelProperties properties) {
        this.geometry = geometry;
        this.properties = properties;
    }
    @JsonProperty("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
    @JsonProperty("properties")
    public HotelProperties getProperties() {
        return properties;
    }

    public void setProperties(HotelProperties properties) {
        this.properties = properties;
    }
}
