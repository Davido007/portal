package com.travalo.holidayservice.models;

        import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * Created by DPLICHTA on 5/17/2017.
 */
public class HolidaysOffersResponse {
    private List<Holidays> holidays;

    public HolidaysOffersResponse() {
    }

    @JsonCreator
    public HolidaysOffersResponse(@JsonProperty("holidays") List<Holidays> holidays) {
        this.holidays = holidays;
    }

    @JsonProperty("holidays")
    public List<Holidays> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Holidays> holidays) {
        this.holidays = holidays;
    }
}
