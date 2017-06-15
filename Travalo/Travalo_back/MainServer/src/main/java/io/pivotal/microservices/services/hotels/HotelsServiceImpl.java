package io.pivotal.microservices.services.hotels;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by DPLICHTA on 5/15/2017.
 */
@Service
@Transactional
public class HotelsServiceImpl implements HotelsService {

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
    public void suggestPlaceName() {

    }
}



