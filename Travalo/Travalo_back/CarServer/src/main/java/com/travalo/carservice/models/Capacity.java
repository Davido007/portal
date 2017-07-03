package com.travalo.carservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by DPLICHTA on 6/19/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Capacity {
    String start;
    String end;

    public Capacity() {
    }

    @JsonCreator
    public Capacity(@JsonProperty("start") String start, @JsonProperty("end") String end) {
        this.start = start;
        this.end = end;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @JsonProperty("end")
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
