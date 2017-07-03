package com.travalo.carservice;

import com.travalo.carservice.models.CarExpediaResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

public interface CarService {

    @RequestMapping("/greeting1")
    public String test1();

    @RequestMapping(method = RequestMethod.GET, value = "/car")
    public CarExpediaResponse getCars(@RequestParam("pickUpCity") String pickUpCity, @RequestParam("dropOffCity") String dropOffCity,
                                      @RequestParam("pickUpDate") String pickUpDate, @RequestParam("dropOffDate") String dropOffDate,
                                      @RequestParam("pickUpCountry") String pickUpCountry, @RequestParam("dropOffCountry") String dropOffCountry);


}