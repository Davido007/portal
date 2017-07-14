package friendsService.models;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by DPLICHTA on 5/17/2017.
 */
public class PlaceResponse {
    private List<Place> places;

    public PlaceResponse() {
    }

    @JsonCreator
    public PlaceResponse(@JsonProperty("places") List<Place> places) {
        this.places = places;
    }

    @JsonProperty("places")
    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
