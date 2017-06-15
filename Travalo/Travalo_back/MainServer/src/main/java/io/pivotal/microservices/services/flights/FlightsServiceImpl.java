package io.pivotal.microservices.services.flights;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.QPXExpressRequestInitializer;
import com.google.api.services.qpxExpress.model.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DPLICHTA on 5/15/2017.
 */
@Service
@Transactional
public class FlightsServiceImpl implements FlightsService {

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
    public void findFlights() {

        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        PassengerCounts passengers = new PassengerCounts();
        passengers.setAdultCount(1);

        List<SliceInput> slices = new ArrayList<SliceInput>();

        SliceInput slice = new SliceInput();
        slice.setOrigin("NYC");
        slice.setDestination("LGA");
        slice.setDate("2017-05-29");
        slices.add(slice);

        TripOptionsRequest request = new TripOptionsRequest();
        request.setSolutions(10);
        request.setSaleCountry("PL");
        request.setPassengers(passengers);
        request.setSlice(slices);

        TripsSearchRequest parameters = new TripsSearchRequest();

        parameters.setRequest(request);
        QPXExpress qpXExpress = new QPXExpress.Builder(httpTransport, JSON_FACTORY, null).setApplicationName(APPLICATION_NAME)
                .setGoogleClientRequestInitializer(new QPXExpressRequestInitializer(API_KEY)).build();

        TripsSearchResponse list = null;
        try {
            list = qpXExpress.trips().search(parameters).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<TripOption> tripResults = list.getTrips().getTripOption();

        String id;

        for (int i = 0; i < tripResults.size(); i++) {
            //Trip Option ID
            id = tripResults.get(i).getId();
            System.out.println("id " + id);

            //Slice
            List<SliceInfo> sliceInfo = tripResults.get(i).getSlice();
            for (int j = 0; j < sliceInfo.size(); j++) {
                int duration = sliceInfo.get(j).getDuration();
                System.out.println("duration " + duration);
                List<SegmentInfo> segInfo = sliceInfo.get(j).getSegment();
                for (int k = 0; k < segInfo.size(); k++) {
                    String bookingCode = segInfo.get(k).getBookingCode();
                    System.out.println("bookingCode " + bookingCode);
                    FlightInfo flightInfo = segInfo.get(k).getFlight();
                    String flightNum = flightInfo.getNumber();
                    System.out.println("flightNum " + flightNum);
                    String flightCarrier = flightInfo.getCarrier();
                    System.out.println("flightCarrier " + flightCarrier);
                    List<LegInfo> leg = segInfo.get(k).getLeg();
                    for (int l = 0; l < leg.size(); l++) {
                        String aircraft = leg.get(l).getAircraft();
                        System.out.println("aircraft " + aircraft);
                        String arrivalTime = leg.get(l).getArrivalTime();
                        System.out.println("arrivalTime " + arrivalTime);
                        String departTime = leg.get(l).getDepartureTime();
                        System.out.println("departTime " + departTime);
                        String dest = leg.get(l).getDestination();
                        System.out.println("Destination " + dest);
                        String destTer = leg.get(l).getDestinationTerminal();
                        System.out.println("DestTer " + destTer);
                        String origin = leg.get(l).getOrigin();
                        System.out.println("origun " + origin);
                        String originTer = leg.get(l).getOriginTerminal();
                        System.out.println("OriginTer " + originTer);
                        int durationLeg = leg.get(l).getDuration();
                        System.out.println("durationleg " + durationLeg);
                        int mil = leg.get(l).getMileage();
                        System.out.println("Milleage " + mil);

                    }

                }
            }

            //Pricing
            List<PricingInfo> priceInfo = tripResults.get(i).getPricing();
            for (int p = 0; p < priceInfo.size(); p++) {
                String price = priceInfo.get(p).getSaleTotal();
                System.out.println("Price " + price);
            }

        }
    }


        /*String request = "https://www.googleapis.com/qpxExpress/v1/trips/search?key=your_API_key_here";
        URL url = null;
        try {
            url = new URL(request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        try {
            System.out.println("yupi");
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("charset", "utf-8");
//        http://partners.api.skyscanner.net/apiservices/browsequotes/v1.0/FR/eur/en-US/uk/us/anytime/anytime?apikey=da789042849785133282915466968595
        //String urlParameters = "apiKey=da789042849785133282915466968595&country=BR&currency=BRL&locale=pt-BR&originplace=SDU&destinationplace=GRU&outbounddate=2016-09-23&locationschema=Iata&adults=1";
        //byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        //int postDataLength = postData.length;

        //conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        System.out.println(conn.getRequestMethod());
        conn.setUseCaches(false);
        System.out.println(conn.getRequestMethod());

        try {
            System.out.println(conn.getRequestMethod());
            int responseCode = conn.getResponseCode();

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : "+ conn.getRequestMethod());
            System.out.println("Response Code : " + responseCode);
            System.out.println("Header Fields : " + conn.getHeaderFields());
            System.out.println("Header Fields : " + conn.getResponseMessage());
        } catch (Exception e) {
            System.out.println("TROLOLO"+e);
        }
        String l = null;
        try {
        BufferedReader br = new BufferedReader(new InputStreamReader(      conn.getInputStream()));

        while ((l=br.readLine())!=null) {
            System.out.println(l);
        }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Flight personValue = null;
        try {
            personValue = objectMapper.readValue("{\"name\":\"Sawyer\"}", Flight.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(personValue.getName());*/
}



