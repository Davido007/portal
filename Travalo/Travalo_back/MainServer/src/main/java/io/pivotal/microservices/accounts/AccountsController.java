package io.pivotal.microservices.accounts;

import com.travalo.carservice.models.CarExpediaResponse;
import com.travalo.carservice.models.CarSearchRequest;
import com.travalo.guideservice.models.GuideApiResponse;
import com.travalo.hotelService.models.HotelResponse;
import com.travalo.hotelService.models.PlaceResponse;
import io.pivotal.microservices.exceptions.AccountNotFoundException;
import io.pivotal.microservices.repositories.UserRepository;
import io.pivotal.microservices.services.flights.FlightsService;
import io.pivotal.microservices.services.user.IUserService;
import io.pivotal.microservices.services.web.AirportsResponse;
import io.pivotal.microservices.services.web.FlightRequest;
import io.pivotal.microservices.services.web.FlightResponse;
import io.pivotal.microservices.utils.GenericResponse;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import userService.DTOs.UserDTO;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


/**
 * A RESTFul controller for accessing account information.
 *
 * @author Paul Chapman
 */
@CrossOrigin(origins = "http://127.0.0.1:9000")
@EnableFeignClients
@RestController
public class AccountsController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    private FlightsClient flightsClient;

    @Autowired
    private HotelsClient hotelClient;

    @Autowired
    private CarClient carClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private HolidaysClient holidaysClient;

    @Autowired
    private GuideClient guideClient;

    @Autowired
    private FriendsClient friendsClient;

    @Autowired
    IUserService service;

    @Autowired
    FlightsService flightService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @Autowired
    private MessageSource messages;

    /**
     * Create an instance plugging in the respository of Accounts.
     *
     * @param userRepository
     *            An account repository implementation.
     */
/*	@Autowired
    public AccountsController(UserRepository userRepository) {
		this.userRepository = userRepository;

	}*/

    /**
     * Fetch an account with the specified account number.
     *
     * @param accountNumber
     *            A numeric, 9 digit account number.
     * @return The account if found.
     * @throws AccountNotFoundException
     *             If the number is not recognised.
     */
/*	@RequestMapping("/accounts/{accountNumber}")
    public Account byNumber(@PathVariable("accountNumber") String accountNumber) {

		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		Account account = accountRepository.findByNumber(accountNumber);
		logger.info("accounts-service byNumber() found: " + account);

		if (account == null)
			throw new AccountNotFoundException(accountNumber);
		else {
			return account;
		}
	}*/

    /**
     * Fetch accounts with the specified name. A partial case-insensitive match
     * is supported. So <code>http://.../accounts/owner/a</code> will find any
     * accounts with upper or lower case 'a' in their name.
     *
     * @param
     * @return A non-null, non-empty set of accounts.
     * @throws AccountNotFoundException If there are no matches at all.
     */
/*	@RequestMapping("/accounts/owner/{name}")
    public List<Account> byOwner(@PathVariable("name") String partialName) {
		logger.info("accounts-service byOwner() invoked: "
				+ accountRepository.getClass().getName() + " for "
				+ partialName);

		List<Account> accounts = accountRepository
				.findByOwnerContainingIgnoreCase(partialName);
		logger.info("accounts-service byOwner() found: " + accounts);

		if (accounts == null || accounts.size() == 0)
			throw new AccountNotFoundException(partialName);
		else {
			return accounts;
		}
	}*/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage(ModelMap model) {
        System.out.println("aaaa");
        return "{\"login\":\"login\"}";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    @ResponseBody
    public userService.utils.GenericResponse registerUserAccount(@RequestBody @Valid final UserDTO accountDto, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", accountDto);

        System.out.println(accountDto.toString());
        return userClient.registerNewUserAccount(accountDto.getUserName(),
                accountDto.getPassword(), accountDto.getMatchingPassword(),
                accountDto.getEmail(), accountDto.isUsing2FA(), request);
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {
        userClient.confirmRegistration(request.getLocale(), token);
        return new ModelAndView("redirect:" + "http://127.0.0.1:9000/#!/emailConfirmed");
    }


/*    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(HttpServletRequest request) {
        String inputLine = null;
        StringBuilder output = new StringBuilder();
        try {
//            System.out.println(pickUpCity);

            // Example of call to the API
            StringBuilder endpoint = new StringBuilder("http://localhost:3339/authenticate");
     //       endpoint.append("pickUpSearchTerm=" + pickUpCity + pickUpCountry);
    *//*        if (dropOffCity != null) {
                endpoint.append("&dropOffSearchTerm=" + dropOffCity + dropOffCountry);
            }*//*
            //  String reformatedPickUpDate = pickUpDate.replace("/","%2F");
          //  endpoint.append("&pickUpDate=" + pickUpDate);
            // String reformatedDropOffDate = dropOffDate.replace("/","%2F");
           // endpoint.append("&dropOffDate=" + dropOffDate);
           // System.out.println(endpoint + "ooooooooooooooooooooooooooooooooo");
//            https://www.expedia.com/carsearch/pickup/list/results?pickUpDate=06%2F25%2F2017&pickUpTime=1030AM&dropOffDate=06%2F27%2F2017&dropOffTime=1030AM&pickUpSearchType=4&pickUpSearchTerm=Warszawa&dropOffSearchTerm=Lodz"
            //endpoint.append(text+"/hotels");

            //String urll = "https://www.expedia.com/carsearch/pickup/list/results?pickUpDate=06%2F25%2F2017&pickUpTime=1030AM&dropOffDate=06%2F27%2F2017&dropOffTime=1030AM&pickUpSearchType=4&pickUpSearchTerm=Warszawa&dropOffSearchTerm=Lodz";
            //URL url = new URL(endpoint.toString());
            //https://www.expedia.com/carsearch/pickup/list/results?pickUpSearchTerm=Warszawa&pickUpDate=06/21/2017&dropOffDate=06/23/2017
            //https://www.expedia.com/carsearch/pickup/list/results?pickUpSearchTerm=Warsaw&pickUpDate=06/21/2017&dropOffDate=06/23/2017
            //https://www.expedia.com/carsearch/pickup/list/results?pickUpSearchTerm=Warsaw&pickUpDate=06/21/2017&dropOffDate=06/23/2017
           // URLConnection connection = url.openConnection();
            //connection.setRequestProperty("api_key", apiKey);
            //connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            //System.out.println(url);
            String url = "http://localhost:3339/authenticate";
            URL obj = new URL(url);
            HttpURLConnection con =  (HttpURLConnection)obj.openConnection();
            con.setDoOutput(true);

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("username", "aaaa");
            con.setRequestProperty("login", "aaaa1");
            String urlParameters  = "username=aaa&password=xxqi1nkv&param3=c";
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
                wr.write( postData );
            }
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            InputStream ins = con.getInputStream();
           InputStreamReader isr = new InputStreamReader(ins);
           BufferedReader in = new BufferedReader(isr);
            //   System.out.println();
            System.out.println(con.getInputStream());

            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
                System.out.println(inputLine);
            }

//            System.out.println(json);
           // in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(output);
*//*        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = userClient.getAuthentication().se;
        securityContext.setAuthentication(auth);*//*
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        securityContext.setAuthentication(authentication);
        if(userClient.isAuthenticated()){
            authentication.setAuthenticated(true);
            securityContext.setAuthentication(authentication);
            //SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
            System.out.println(true);
            System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        } else {
            System.out.println(false);
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        }
        Authentication authentication1 =  new UsernamePasswordAuthenticationToken(person, null, person.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication1);
*//*        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);*//*
        return output.toString();
    }*/


    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUserAccount() {
        System.out.println("security");
        //System.out.println("nsns" + userRepository);
        //System.out.println(userService.utils.SecurityUtils.getCurrentLogin());
        User user = userRepository.findByLogin(SecurityUtils.getCurrentLogin());

        System.out.println("lll" + user);
        //user.setPassword(null);
        return user;
    }

    @RequestMapping(value = "/searchFriends", method = RequestMethod.POST)
    public List<userService.models.User> searchFriends(@RequestBody final String login) {
        System.out.println("here");
//        guideClient.getSuggestions("yolo");
        //System.out.println(guideClient.getSuggestions("yolo"));
        // flightService.findFlights();
        // System.out.println(hotelClient.test("Ł"));
        //System.out.println(greetingClient.test());
        //AirportsResponse airports = flightsClient.getAirport("W");
        //  System.out.println(airports.size()+"aaaaaaaaaaaaaaa");
        System.out.println(login);
        //List<userService.models.User> foundUsers = userRepository.findByLoginStartingWith(login);
        // System.out.println(foundUsers);
        return null;
        //return friendsClient.searchFriends("Yolo");
    }

    @RequestMapping(value = "/flights12", method = RequestMethod.POST)
    public
    @ResponseBody
    GenericResponse findFlights(@RequestBody final String city) {
        System.out.println("here");
        // flightService.findFlights();
        // System.out.println(hotelClient.test("Ł"));
        //System.out.println(greetingClient.test());
        AirportsResponse airports = flightsClient.getAirport("W");
        //  System.out.println(airports.size()+"aaaaaaaaaaaaaaa");
        return new GenericResponse("oki");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public GuideApiResponse test() {
        System.out.println("here");
//        guideClient.getSuggestions("yolo");
        //System.out.println(guideClient.getSuggestions("yolo"));
        // flightService.findFlights();
        // System.out.println(hotelClient.test("Ł"));
        //System.out.println(greetingClient.test());
        //AirportsResponse airports = flightsClient.getAirport("W");
        //  System.out.println(airports.size()+"aaaaaaaaaaaaaaa");
        return guideClient.getSuggestions("yolo");
    }

    @RequestMapping(value = "/airports", method = RequestMethod.POST)
    public
    @ResponseBody
    AirportsResponse getAirports(@RequestBody final String text) {
        System.out.println("here");
        // flightService.findFlights();
        // System.out.println(hotelClient.test("Ł"));
        //System.out.println(greetingClient.test());
        AirportsResponse airports = flightsClient.getAirport(text);
        //  System.out.println(airports.size()+"aaaaaaaaaaaaaaa");
        return airports;
    }

    @RequestMapping(value = "/flights", method = RequestMethod.POST)
    public
    @ResponseBody
    FlightResponse getFlights(@RequestBody final FlightRequest request) {
        System.out.println("here" + request.toString());
        // flightService.findFlights();
        // System.out.println(hotelClient.test("Ł"));
        //System.out.println(greetingClient.test());
        System.out.println(request.getIsNonStop());
        FlightResponse flights = flightsClient.getFlights(request.getOrigin().getIata(), request.getDestination().getIata(),
                request.getAdults(), request.getChildren(), request.getDepartureDate(), request.getReturnDate(), request.getIsNonStop());
        //  System.out.println(airports.size()+"aaaaaaaaaaaaaaa");
        FlightResponse flights1 = new FlightResponse();

        return flights;
/*        System.out.println("here");
        FlightResponse flights;
        System.out.println(request.getOrigin().getIcao());
        System.out.println(request.toString());
        flights = flightsClient.getFlights(new FlightRequest(request.getOrigin(),request.getDestination(),
                request.getAdults(),request.getChildren()));
        return flights;*/
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public
    @ResponseBody
    CarExpediaResponse getCars(@RequestBody final CarSearchRequest request) {
        //System.out.println("here" + request.toString());
        // System.out.println(request.getIsNonStop());
        System.out.println(request.getReturnCity());
        // FlightResponse flights1 = new FlightResponse();
        CarExpediaResponse response = carClient.getCars(request.getOriginCity(), request.getReturnCity(), request.getPickUpDate(),
                request.getDropOffDate(), request.getOriginCountry(), request.getReturnCountry());
/*        System.out.println(carClient.getCars(request.getOriginCity(), request.getReturnCity(), request.getPickUpDate(),
                request.getDropOffDate(), request.getOriginCountry(), request.getReturnCountry()));*/
        return response;

    }


    @RequestMapping(value = "/suggestion", method = RequestMethod.POST)
    public
    @ResponseBody
    PlaceResponse getSuggestions(@RequestBody final String city) {
        System.out.println("here");
        System.out.println(city);
        // flightService.findFlights();
        //  System.out.println(hotelClient.test(city));
        PlaceResponse places = hotelClient.getSuggestions(city);
        System.out.println(places.getPlaces().size());
        return places;
    }

    @RequestMapping(value = "/hotels", method = RequestMethod.POST)
    public
    @ResponseBody
    HotelResponse getHotels(@RequestBody final String href) {
        System.out.println("here");
        System.out.println(href);
        // flightService.findFlights();
        //  System.out.println(hotelClient.test(city));
        HotelResponse hotels = hotelClient.getHotels(href);
        System.out.println(hotels.getHotels().size());
        return hotels;
    }

    /*	private User createUserAccount(UserDTO accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }*/
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    /**
     * Created by DPLICHTA on 5/8/2017.
     */
    @Entity
    @Table(name = "authority")
    public static class Authority {

        @Id
        @GenericGenerator(name = "generator", strategy = "increment")
        @GeneratedValue(generator = "generator")
        private Long id;

        @Column(name = "name", nullable = false)
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
