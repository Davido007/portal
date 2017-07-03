package com.travalo.holidayservice.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travalo.holidayservice.models.Holidays;
import com.travalo.holidayservice.models.HolidaysApiResponse;
import com.travalo.holidayservice.models.HolidaysOffersResponse;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DPLICHTA on 5/15/2017.
 */
@RestController
public class HolidaysServiceImpl implements HolidaysService {

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
    public HolidaysOffersResponse getHolidays(String text) {
        StringBuilder json = new StringBuilder();
        StringBuilder holidaysJson = new StringBuilder();
        try {
            File file = new File(".");
            for(String fileNames : file.list()) System.out.println(fileNames);
            // Example of call to the API
            StringBuilder endpoint = new StringBuilder("https://www.expedia.com/Hotel-Search-Data?responsive=true&timezoneOffset=7200000" +
                    "&siteid=1&langid=1033&packageType=fh&sort=recommended&endDate=6/30/2017&startDate=6/21/2017&ttla=ZTH&origin=Warsaw&destination=Zakynthos+Island,+Greece&adults=1&c=afcf4e18-5000-4cc8-b130-9c5e8525b819&searchProduct=hotel&packageType=fh&packageType=fh&hsrIdentifier=HSR");
            URL url = new URL(endpoint.toString());
            URLConnection connection = url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            System.out.println(url);
            InputStream ins = new FileInputStream(new File("src/main/resources/holidaysJson.txt"));
            //InputStream ins = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);
            //   System.out.println();

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
        HolidaysApiResponse myObjects = null;
        // PlaceResponse personValue = null;
        try {
            myObjects = objectMapper.readValue(json.toString(), new TypeReference<HolidaysApiResponse>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Holidays> choosenHolidays = new ArrayList<Holidays>();
        System.out.println(myObjects.getResults().size());
        for (int i = 0; i < myObjects.getResults().size(); i++) {
            Holidays holidays = new Holidays();
            holidays.setHotelName(myObjects.getResults().get(i).getRetailHotelInfoModel().getHotelName());
            holidays.setHotelDescription(myObjects.getResults().get(i).getRetailHotelInfoModel().getHotelDescription());
            holidays.setBigThumbnailURL(myObjects.getResults().get(i).getRetailHotelInfoModel().getBigThumbnailURL());
            holidays.setNeighborhoodOrCityName(myObjects.getResults().get(i).getRetailHotelInfoModel().getNeighborhoodOrCityName());
            holidays.setHotelStarRating(myObjects.getResults().get(i).getHotelStarRating());
            holidays.setPriceFormatted(myObjects.getResults().get(i).getRetailHotelPricingModel().getPriceFormatted());
            choosenHolidays.add(holidays);
           // choosenHolidays.add(myObjects.getResults().get(i));
            if (i == 50) {
                break;
            }
            System.out.println(myObjects.getResults().get(i).getHotelStarRating());
        }
       // myObjects.setResults(choosenHolidays);
        HolidaysOffersResponse holidays = new HolidaysOffersResponse();
        //HotelResponse hotels = new HotelResponse();
        //hotels.setHotels(myObjects);
       // return myObjects;
        holidays.setHolidays(choosenHolidays);
        return holidays;
    }

}



