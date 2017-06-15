package io.pivotal.microservices.services.web;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by DPLICHTA on 6/12/2017.
 */
public class FlightRequest {
    private Airport origin;
    private Airport destination;
    private int adults;
    private int children;
    private String departureDate;
    private String returnDate;
    private boolean isNonStop;

    public FlightRequest() {
    }

    @JsonCreator
    public FlightRequest(@JsonProperty("origin") Airport origin, @JsonProperty("destination") Airport destination,
                         @JsonProperty("adults") int adults, @JsonProperty("children") int children,
                         @JsonProperty("departureDate") String departureDate, @JsonProperty("returnDate") String returnDate,
                         @JsonProperty("isNonStop") boolean isNonStop) {
        this.origin = origin;
        this.destination = destination;
        this.adults = adults;
        this.children = children;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.isNonStop = isNonStop;
    }

    @JsonProperty("children")
    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @JsonProperty("origin")
    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    @JsonProperty("destination")
    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    @JsonProperty("adults")
    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    @JsonProperty("departureDate")
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @JsonProperty("returnDate")
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @JsonProperty("isNonStop")
    public boolean getIsNonStop() {
        return isNonStop;
    }

    public void setIsNonStop(boolean nonStop) {
        isNonStop = nonStop;
    }

    @Override
    public String toString() {
        return "FlightRequest{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", adults=" + adults +
                ", children=" + children +
                ", departureDate='" + departureDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", isNonStop=" + isNonStop +
                '}';
    }
}
