package io.pivotal.microservices.accounts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by DPLICHTA on 5/15/2017.
 */
public class Flight {

    private String name;

    @JsonCreator
    public Flight(@JsonProperty("name") String name) {
        this.name = name;
    }

    // getters and setters

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name: ")
                .append(this.name).append("\n");


        return stringBuilder.toString();
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
}