package friendsService.models;

        import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by DPLICHTA on 5/17/2017.
 */
public class HotelResponse {
    private List<Hotel> hotels;

    public HotelResponse() {
    }

    @JsonCreator
    public HotelResponse(@JsonProperty("hotels") List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @JsonProperty("hotels")
    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }
}
