package io.pivotal.microservices.services.web;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * Created by DPLICHTA on 5/17/2017.
 */
public class Travel {
    private Flight mainFlight;
    private Flight returnFlight;
    private String price;

    @JsonCreator
    public Travel(@JsonProperty("mainFlight") Flight mainFlight, @JsonProperty("returnFlight") Flight returnFlight,
                  @JsonProperty("price") String price ) {
        this.mainFlight = mainFlight;
        this.returnFlight = returnFlight;
        this.price = price;
    }

    public Travel() {
    }

    @JsonProperty("mainFlight")
    public Flight getMainFlight() {
        return mainFlight;
    }

    public void setMainFlight(Flight mainFlight) {
        this.mainFlight = mainFlight;
    }

    @JsonProperty("mainFlight")
    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
