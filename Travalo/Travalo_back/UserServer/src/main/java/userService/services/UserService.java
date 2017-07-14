package userService.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import userService.models.User;
import userService.models.VerificationToken;
import userService.utils.GenericResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    GenericResponse registerNewUserAccount(@RequestParam("userName") String userName,
                                           @RequestParam("password") String password,
                                           @RequestParam("matchingPassword") String matchingPassword,
                                           @RequestParam("email") String email,
                                           @RequestParam("isUsing2FA") boolean isUsing2FA,
                                           @RequestParam("locale") HttpServletRequest request);

    @RequestMapping(method = RequestMethod.GET, value = "/createVerificationToken")
    void createVerificationToken(@RequestParam("user") User user, @RequestParam("token") String token);

    @RequestMapping(method = RequestMethod.GET, value = "/confirmRegistration")
    GenericResponse confirmRegistration(@RequestParam("locale") Locale locale, @RequestParam("token") String token);

    @RequestMapping(method = RequestMethod.GET, value = "/getVerificationToken")
    VerificationToken getVerificationToken(@RequestParam("locale") String verificationToken);

    @RequestMapping(method = RequestMethod.GET, value = "/searchFriends")
    public String searchFriends(@RequestParam("text") String text);

    @RequestMapping(method = RequestMethod.GET, value = "/isAuthenticated")
    public boolean isAuthenticated();

}