package io.pivotal.microservices.services.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

public interface FlightService {

    @RequestMapping("/greeting")
    public FlightResponse test();

    @RequestMapping("/greeting1")
    public String test1();

    @RequestMapping(method = RequestMethod.GET, value = "/airports")
    public AirportsResponse getAirport(@RequestParam("text") String text);

    @RequestMapping(method = RequestMethod.GET, value = "/flights")
    public FlightResponse getFlights(@RequestParam("origin") String origin,
                                     @RequestParam("destination") String destination,
                                     @RequestParam("adults") int adults,
                                     @RequestParam("children") int children,
                                     @RequestParam("departureDate") String departureDate,
                                     @RequestParam("returnDate") String returnDate,
                                     @RequestParam("isNonStop") boolean isNonStop);


}