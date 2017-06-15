package com.travalo.hotelService.services;

import com.travalo.hotelService.models.PlaceResponse;
import com.travalo.hotelService.models.HotelResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

public interface HotelService {

    @RequestMapping(method = RequestMethod.GET, value = "/suggestion")
    public PlaceResponse getSuggestions(@RequestParam("text") String text);

    @RequestMapping(method = RequestMethod.GET, value = "/hotel")
    public HotelResponse getHotels(@RequestParam("text") String text);

}