package com.travalo.holidayservice.services;


import com.travalo.holidayservice.models.HolidaysOffersResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

public interface HolidaysService {

    @RequestMapping(method = RequestMethod.GET, value = "/holidays")
    public HolidaysOffersResponse getHolidays(@RequestParam("text") String text);

}