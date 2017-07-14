package friendsService.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelProperties {
    private String title;
    private String image;

    public HotelProperties() {
    }

    @JsonCreator
    public HotelProperties(@JsonProperty("title") String title, @JsonProperty("image") String image) {
        this.title = title;
        this.image = image;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
