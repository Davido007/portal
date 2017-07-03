package com.travalo.carservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.travalo.carservice.models.CarExpediaResponse;
import com.travalo.carservice.models.CarOffers;
import org.springframework.web.bind.annotation.RequestParam;
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
public class CarServiceImpl implements CarService {

    private static final String APPLICATION_NAME = "MyFlightApplication";

    private static final String API_KEY = "AIzaSyCJZzEKjIHNWpwPPxFrkcbxUcvPlbXaGVA";

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport httpTransport;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


    @Override
    public String test1() {
        System.out.println("hereCarrs");
        return "hello from cars";
    }

    /*    @Override
        public String getCars(@RequestParam("request") String request) {

          //  System.out.println(request.getOriginCity());
            //  System.out.println(request.getReturnCity());
            System.out.println(request);
            return "sukces";
            //https://www.expedia.com/carsearch/pickup/list/results?pickUpDate=06%2F15%2F2017
            // &pickUpTime=1030AM&dropOffDate=06%2F17%2F2017&dropOffTime=1030AM&pickUpSearchType=4&pickUpSearchTerm=Košice
        }*/
    @Override
    public CarExpediaResponse getCars(@RequestParam("pickUpCity") String pickUpCity, @RequestParam("dropOffCity") String dropOffCity,
                                      @RequestParam("pickUpDate") String pickUpDate, @RequestParam("dropOffDate") String dropOffDate,
                                      @RequestParam("pickUpCountry") String pickUpCountry, @RequestParam("dropOffCountry") String dropOffCountry) {
        System.out.println("Wlazł");
        StringBuilder json = new StringBuilder();
        try {
            System.out.println(pickUpCity);

            // Example of call to the API
            StringBuilder endpoint = new StringBuilder("https://www.expedia.com/carsearch/pickup/list/results?");
            endpoint.append("pickUpSearchTerm=" + pickUpCity + pickUpCountry);
            if (dropOffCity != null) {
                endpoint.append("&dropOffSearchTerm=" + dropOffCity + dropOffCountry);
            }
            //  String reformatedPickUpDate = pickUpDate.replace("/","%2F");
            endpoint.append("&pickUpDate=" + pickUpDate);
            // String reformatedDropOffDate = dropOffDate.replace("/","%2F");
            endpoint.append("&dropOffDate=" + dropOffDate);
            System.out.println(endpoint + "ooooooooooooooooooooooooooooooooo");
//            https://www.expedia.com/carsearch/pickup/list/results?pickUpDate=06%2F25%2F2017&pickUpTime=1030AM&dropOffDate=06%2F27%2F2017&dropOffTime=1030AM&pickUpSearchType=4&pickUpSearchTerm=Warszawa&dropOffSearchTerm=Lodz"
            //endpoint.append(text+"/hotels");

            //String urll = "https://www.expedia.com/carsearch/pickup/list/results?pickUpDate=06%2F25%2F2017&pickUpTime=1030AM&dropOffDate=06%2F27%2F2017&dropOffTime=1030AM&pickUpSearchType=4&pickUpSearchTerm=Warszawa&dropOffSearchTerm=Lodz";
            URL url = new URL(endpoint.toString());
            //https://www.expedia.com/carsearch/pickup/list/results?pickUpSearchTerm=Warszawa&pickUpDate=06/21/2017&dropOffDate=06/23/2017
            //https://www.expedia.com/carsearch/pickup/list/results?pickUpSearchTerm=Warsaw&pickUpDate=06/21/2017&dropOffDate=06/23/2017
            //https://www.expedia.com/carsearch/pickup/list/results?pickUpSearchTerm=Warsaw&pickUpDate=06/21/2017&dropOffDate=06/23/2017
            URLConnection connection = url.openConnection();
            //connection.setRequestProperty("api_key", apiKey);
            //connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            System.out.println(url);
            InputStream ins = connection.getInputStream();
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
        CarExpediaResponse myObjects = null;
        // PlaceResponse personValue = null;
        try {
            myObjects = objectMapper.readValue(json.toString(), new TypeReference<CarExpediaResponse>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<CarOffers> choosenOffers = new ArrayList<CarOffers>();
        System.out.println(myObjects.getOffers().size());
        for (int i = 0; i < myObjects.getOffers().size(); i++) {
            choosenOffers.add(myObjects.getOffers().get(i));
            if (i == 50) {
                break;
            }
            System.out.println(myObjects.getOffers().get(i).getPiid());
            System.out.println(myObjects.getOffers().get(i).getFare().getTotal().getValue());
            System.out.println(myObjects.getOffers().get(i).getPickUpLocation().getAddress());
            System.out.println(myObjects.getOffers().get(i).getVehicle().getDescription());
            System.out.println(myObjects.getOffers().get(i).getVendor().getName());
        }
        myObjects.setOffers(choosenOffers);
        //HotelResponse hotels = new HotelResponse();
        //hotels.setHotels(myObjects);
        return myObjects;
    }
}



