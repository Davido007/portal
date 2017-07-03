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
import io.pivotal.microservices.users.UserDTO;
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

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;


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
    private FlightsClient flightsClient;

    @Autowired
    private HotelsClient hotelClient;

    @Autowired
    private CarClient carClient;

    @Autowired
    private HolidaysClient holidaysClient;

    @Autowired
    private GuideClient guideClient;

    @Autowired
    IUserService service;

    @Autowired
    FlightsService flightService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    protected UserRepository userRepository;

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
    public GenericResponse registerUserAccount(@RequestBody @Valid final UserDTO accountDto, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", accountDto);
        System.out.println(accountDto.toString());
        final User registered = service.registerNewUserAccount(accountDto);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            //  return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        System.out.println(verificationToken);
        System.out.println(verificationToken.getExpiryDate());


        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            // return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        service.saveRegisteredUser(user);
        return new ModelAndView("redirect:" + "http://127.0.0.1:9000/#!/emailConfirmed");
    }


    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public
    @ResponseBody
    User getUserAccount() {
        System.out.println("nsns" + userRepository);
        System.out.println(SecurityUtils.getCurrentLogin());
        User user = userRepository.findByLogin(SecurityUtils.getCurrentLogin());
        System.out.println("lll" + user);
        user.setPassword(null);
        return user;
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
