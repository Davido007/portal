package io.pivotal.microservices.services.web;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.QPXExpressRequestInitializer;
import com.google.api.services.qpxExpress.model.*;
import io.pivotal.microservices.services.web.repositories.AirlineRepository;
import io.pivotal.microservices.services.web.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DPLICHTA on 5/15/2017.
 */
@RestController
public class FlightServiceImpl implements FlightService {

    private static final String APPLICATION_NAME = "MyFlightApplication";

    private static final String API_KEY = "AIzaSyCJZzEKjIHNWpwPPxFrkcbxUcvPlbXaGVA";

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport httpTransport;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    AirlineRepository airlineRepository;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    @Override
    public AirportsResponse getAirport(String text) {
        List<Airport> airports;
        airports = airportRepository.findByNameStartingWith(text);
        AirportsResponse response = new AirportsResponse();
        List choosenAirports = new ArrayList();
        for (int i = 0; i < airports.size(); i++) {
            if (!airports.get(i).getIata().equals("\\N")) {
                choosenAirports.add(airports.get(i));
            }
            if (choosenAirports.size() == 10) {
                break;
            }
        }
        response.setAirports(choosenAirports);
        System.out.println(text + "a" + choosenAirports.size());
        return response;
    }

    @Override
    public FlightResponse getFlights(String origin, String destination, int adults, int children, String departureDate,
                                     String returnDate, boolean isNonStop) {
        List airports;
        // airports = airportRepository.findByNameStartingWith(text);
        //  AirportsResponse response = new AirportsResponse();
/*        List choosenAirports = new ArrayList();
        for(int i=0 ; i<airports.size(); i++){
            choosenAirports.add(airports.get(i));
            if(i==10){
                break;
            }
        }*/
        // response.setAirports(choosenAirports);
        //   System.out.println(text+"a"+choosenAirports.size());
        System.out.println(origin + " " + destination + " " + adults + " " + children + departureDate + returnDate + isNonStop);
/*        System.out.println(request.getOrigin().toString());
        System.out.println(request.getOrigin().getIcao() + request.getDestination().getIcao()
                + request.getAdults() + request.getChildren());*/
        System.out.println("lol");
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        PassengerCounts passengers = new PassengerCounts();
        passengers.setAdultCount(adults);
        passengers.setChildCount(children);

        List<SliceInput> slices = new ArrayList<SliceInput>();

        SliceInput slice = new SliceInput();
        slice.setOrigin(origin);
        slice.setDestination(destination);
        slice.setDate(departureDate);
        if (isNonStop == true) {
            slice.setMaxStops(0);
        }
        slices.add(slice);

        SliceInput returnSlice = new SliceInput();
        returnSlice.setOrigin(destination);
        returnSlice.setDestination(origin);
        returnSlice.setDate(returnDate);
        if (isNonStop == true) {
            returnSlice.setMaxStops(0);
        }
        slices.add(returnSlice);

        TripOptionsRequest request = new TripOptionsRequest();
        request.setSolutions(10);
        request.setSaleCountry("US");
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
        FlightResponse response = new FlightResponse();
        List<Travel> travels = new ArrayList<Travel>();
        for (int i = 0; i < tripResults.size(); i++) {
            Travel travel = new Travel();
            //List<Flight> flights = new ArrayList<Flight>();
            //Trip Option ID
            id = tripResults.get(i).getId();
            System.out.println("id " + id);

            //Slice
            List<SliceInfo> sliceInfo = tripResults.get(i).getSlice();
            for (int j = 0; j < sliceInfo.size(); j++) {
                Flight flight = new Flight();
                flight.setDirect(true);
                int duration = sliceInfo.get(j).getDuration();
                int calculatedHours = duration / 60;
                int calculatedMinutes = duration - (calculatedHours * 60);
                flight.setDuration(calculatedHours + "h " + calculatedMinutes + "m");
                System.out.println(flight.getDuration());

                //System.out.println("Flightssssssssss" + flights.size());
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
                    if (k > 0) {
                        flight.setDirect(false);
                    }
                    for (int l = 0; l < leg.size(); l++) {
                        String aircraft = leg.get(l).getAircraft();
                        System.out.println("aircraft " + aircraft);
                        String arrivalTime = leg.get(l).getArrivalTime();
                        System.out.println("arrivalTime " + arrivalTime);
                        //  2017-06-27T17:05+02:00
                        String aDate = arrivalTime.split("T")[0];
                        String aTime = arrivalTime.split("T")[1].split("\\+")[0];
                        flight.setArrivalDate(aDate);
                        flight.setArrivalTime(aTime);
                        String departTime = leg.get(l).getDepartureTime();
                        System.out.println("departTime " + departTime);
                        String dest = leg.get(l).getDestination();
                        String origins = leg.get(l).getOrigin();
                        if (k == 0) {
                            flight.setOrigin(origins);
                        }
                        if (l == leg.size() - 1) {
                            flight.setDestination(dest);
                        }

                        System.out.println("Destination " + dest);
                        String destTer = leg.get(l).getDestinationTerminal();
                        System.out.println("DestTer " + destTer);
                        flight.setCarrier(airlineRepository.findAllByIata(flightInfo.getCarrier()).get(0).getName());
                        if (k == 0) {
                            String dDate = departTime.split("T")[0];
                            String dTime = departTime.split("T")[1].split("\\+")[0];
                            flight.setDepartureDate(dDate);
                            flight.setDepartureTime(dTime);

                        } else {
                            flight.setThrough(leg.get(l).getOrigin());
                        }
                        System.out.println("origin " + origins);
                        String originTer = leg.get(l).getOriginTerminal();
                        System.out.println("OriginTer " + originTer);
                        int durationLeg = leg.get(l).getDuration();
                        System.out.println("durationleg " + durationLeg);
                        int mil = leg.get(l).getMileage();
                        System.out.println("Milleage " + mil);
                        //  System.out.println("Flightssssssssss1" + flights.size());
                    }

                }
                if (j == 0) {
                    travel.setMainFlight(flight);
                } else {
                    travel.setReturnFlight(flight);
                }
            }

            //Pricing
            List<PricingInfo> priceInfo = tripResults.get(i).getPricing();
            for (int p = 0; p < priceInfo.size(); p++) {
                String price = priceInfo.get(p).getSaleTotal();
                System.out.println("Price " + price);
                travel.setPrice(price);
            }
            travels.add(travel);
//            System.out.println("Flightssssssssss2"+flights.get(1).getDuration());
        }

        List<Travel> choosenOneDirectTravels = new ArrayList<Travel>();
        List<Travel> choosenAllDirectTravels = new ArrayList<Travel>();
        List<Travel> choosenInDirectTravels = new ArrayList<Travel>();
        for (int i = 0; i < travels.size(); i++) {
            if (travels.get(i).getMainFlight().isDirect() == true && travels.get(i).getReturnFlight().isDirect() == true) {
                choosenAllDirectTravels.add(travels.get(i));
            } else if (travels.get(i).getMainFlight().isDirect() == true) {
                choosenOneDirectTravels.add(travels.get(i));
            } else {
                choosenInDirectTravels.add(travels.get(i));
            }
        }
        List<Travel> sortedTravels = new ArrayList<Travel>();
        sortedTravels.addAll(choosenAllDirectTravels);
        sortedTravels.addAll(choosenOneDirectTravels);
        sortedTravels.addAll(choosenInDirectTravels);

        response.setTravels(sortedTravels);
        return response;
        //   return new FlightResponse();
    }

    @Override
    public FlightResponse test() {
        System.out.println("lol");
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
        slice.setDestination("WAW");
        slice.setDate("2017-06-29");
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
        FlightResponse response = new FlightResponse();
        List<Flight> flights = new ArrayList<Flight>();
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
                        Flight flight = new Flight();
                        flight.setDestination(leg.get(l).getDestination());
                        flight.setOrigin(leg.get(l).getOrigin());
                        flights.add(flight);
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
                        System.out.println("origin " + origin);
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


        //  response.setFlights(flights);
        return response;
    }

    @Override
    public String test1() {
        try {
            // Your API Key and secret
            String apiKey = "7pgm3fzexg4fwyr9v7efguwy";
            String Secret = "RUsEyXKAbT";
            // Signature is generated by SHA256 (Api-Key + Secret +
            // Timestamp (in seconds))
            String signature = org.apache.commons.codec.digest.DigestUtils
                    .sha256Hex(apiKey + Secret + System.currentTimeMillis() / 1000);

            // Example of call to the API
            String endpoint = "https://api.test.hotelbeds.com/hotel-api/1.0/status";
            URL url = new URL(endpoint);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("X-Signature", signature);
            connection.setRequestProperty("Api-Key", apiKey);
            connection.setRequestProperty("Accept", "application/xml");

            InputStream ins = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "aa";
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



