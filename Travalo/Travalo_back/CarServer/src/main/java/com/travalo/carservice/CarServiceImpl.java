package com.travalo.carservice;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.travalo.carservice.models.CarSearchRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public String getCars(@RequestParam("request") CarSearchRequest request) {
        System.out.println(request.getOriginCity());
        System.out.println(request.getReturnCity());
        return "sukces";
    }
}



