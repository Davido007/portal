package com.travalo.guideservice.services;


import com.travalo.guideservice.models.GuideApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

public interface GuideService {

    @RequestMapping(method = RequestMethod.GET, value = "/guides")
    public GuideApiResponse getSuggestions(@RequestParam("text") String text);

}