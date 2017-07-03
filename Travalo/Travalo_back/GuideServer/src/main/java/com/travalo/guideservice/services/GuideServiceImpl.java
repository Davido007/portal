package com.travalo.guideservice.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travalo.guideservice.models.GuideApiResponse;
import com.travalo.guideservice.models.Itinerary;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DPLICHTA on 5/15/2017.
 */
@RestController
public class GuideServiceImpl implements GuideService {

    private static final String APPLICATION_NAME = "MyFlightApplication";

    private static final String API_KEY = "AIzaSyCJZzEKjIHNWpwPPxFrkcbxUcvPlbXaGVA";

    private static final String CITY_ICON="https://www.tripexpert.com/assets/tripexpert/pin@2x-231f842561316cf6f01ac691edc079f81f46e701ec59cf1dc470ff773635f3c9.png";

    /**
     * Global instance of the HTTP transport.
     */

    /**
     * Global instance of the JSON factory.
     */

    @Override
    public GuideApiResponse getSuggestions(String text) {
        StringBuilder json = new StringBuilder();
        try {
            // Example of call to the API
            StringBuilder endpoint = new StringBuilder("http://d2sdiix1bd21d9.cloudfront.net/jsonws/clm-20171214115627/Paris/city-data");
            URL url = new URL(endpoint.toString());
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            InputStream ins = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
                System.out.println(inputLine);
            }
            System.out.println(json);
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        GuideApiResponse myObjects = null;
        // PlaceResponse personValue = null;
        try {
            myObjects = objectMapper.readValue(json.toString(), new TypeReference<GuideApiResponse>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Itinerary> choosenOffers = new ArrayList<Itinerary>();
        System.out.println(myObjects.getTopItins().size());
        for (int i = 0; i < myObjects.getTopItins().size(); i++) {
            choosenOffers.add(myObjects.getTopItins().get(i));
            if (i == 50) {
                break;
            }
            System.out.println(myObjects.getTopItins().get(i).getDescription());
        }
        myObjects.setTopItins(choosenOffers);
        //HotelResponse hotels = new HotelResponse();
        //hotels.setHotels(myObjects);
        return myObjects;
    }


}



