package userService.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 6/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    private String label;
    private String permalink;
    private String location;
    private String href;
    private String icon;

    public Place() {
    }

    @JsonCreator
    public Place(@JsonProperty("label") String label, @JsonProperty("permalink") String permalink,
                 @JsonProperty("location") String location, @JsonProperty("href") String href, @JsonProperty("icon") String icon) {
        this.label = label;
        this.permalink = permalink;
        this.location = location;
        this.href = href;
        this.icon = icon;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("permalink")
    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
