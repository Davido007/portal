package com.travalo.hotelService.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travalo.hotelService.models.PlaceResponse;
import com.travalo.hotelService.models.Hotel;
import com.travalo.hotelService.models.HotelResponse;
import com.travalo.hotelService.models.Place;
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
public class HotelServiceImpl implements HotelService {

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
    public PlaceResponse getSuggestions(String text) {
        StringBuilder json = new StringBuilder();
        try {
            System.out.println(text);
            // Your API Key and secret
            String apiKey = "833582bd7cbc5cdfe7178e53df3f67df";

            // Example of call to the API
            StringBuilder endpoint = new StringBuilder("https://api.tripexpert.com/suggestions");
            endpoint.append("?term=" + text);
            URL url = new URL(endpoint.toString());

            URLConnection connection = (URLConnection) url.openConnection();
            //connection.setRequestProperty("api_key", apiKey);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            connection.setRequestProperty("Accept", "application/json");
            System.out.println(url);
            InputStream ins = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<Place> myObjects = null;
        try {
            myObjects = objectMapper.readValue(json.toString(), new TypeReference<List<Place>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Place> citiesFromPlaces = new ArrayList<Place>();
        for(int i=0; i<myObjects.size();i++){
            if(myObjects.get(i).getIcon().equals(CITY_ICON) && myObjects.get(i).getLabel().toUpperCase().charAt(0)==text.toUpperCase().charAt(0)){
                citiesFromPlaces.add(myObjects.get(i));
            }
        }
        PlaceResponse places = new PlaceResponse();
        places.setPlaces(citiesFromPlaces);
        System.out.println(places.getPlaces().size());
        return places;
    }

    @Override
    public HotelResponse getHotels(String text) {
        StringBuilder json = new StringBuilder();
        try {
            System.out.println(text);
            // Your API Key and secret
            String apiKey = "833582bd7cbc5cdfe7178e53df3f67df";

            // Example of call to the API
            StringBuilder endpoint = new StringBuilder("https://api.tripexpert.com");
            endpoint.append(text+"/hotels");
            URL url = new URL(endpoint.toString());

            URLConnection connection = (URLConnection) url.openConnection();
            //connection.setRequestProperty("api_key", apiKey);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            connection.setRequestProperty("Accept", "application/json");
            System.out.println(url);
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
        List<Hotel> myObjects = null;
        PlaceResponse personValue = null;
        try {
            myObjects = objectMapper.readValue(json.toString(), new TypeReference<List<Hotel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(myObjects.size());
        for(int i=0; i<myObjects.size();i++){
            myObjects.get(i);
        }
        HotelResponse hotels = new HotelResponse();
        hotels.setHotels(myObjects);
        return hotels;
    }

}



