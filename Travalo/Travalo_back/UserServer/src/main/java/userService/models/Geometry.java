package userService.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private String type;
    private String[] coordinates;

    public Geometry() {
    }

    @JsonCreator
    public Geometry(@JsonProperty("type") String type, @JsonProperty("coordinates") String[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("coordinates")
    public String[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }
}
