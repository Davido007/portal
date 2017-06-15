package io.pivotal.microservices.services.web;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by DPLICHTA on 5/17/2017.
 */
public class Flight {
    private String origin;
    private String destination;
    private String duration;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private boolean isDirect;
    private String through;
    private String carrier;

    @JsonCreator
    public Flight(@JsonProperty("origin") String origin, @JsonProperty("destination") String destination,
                  @JsonProperty("duration") String duration, @JsonProperty("departureDate") String departureDate,
                  @JsonProperty("arrivalDate") String arrivalDate, @JsonProperty("departureTime") String departureTime,
                  @JsonProperty("arrivalTime") String arrivalTime, @JsonProperty("isDirect") boolean isDirect,
                  @JsonProperty("through") String through, @JsonProperty("carrier") String carrier) {
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.isDirect = isDirect;
        this.through = through;
        this.carrier = carrier;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Flight() {
    }

    @JsonProperty("origin")
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @JsonProperty("destination")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @JsonProperty("departureTime")
    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @JsonProperty("arrivalTime")
    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @JsonProperty("isDirect")
    public boolean isDirect() {
        return isDirect;
    }

    public void setDirect(boolean direct) {
        isDirect = direct;
    }

    @JsonProperty("through")
    public String getThrough() {
        return through;
    }

    public void setThrough(String through) {
        this.through = through;
    }

    @JsonProperty("carrier")
    public String getCarrier() {
        return carrier;
    }

    @JsonProperty("departureDate")
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @JsonProperty("arrivalDate")
    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;

    }
}
