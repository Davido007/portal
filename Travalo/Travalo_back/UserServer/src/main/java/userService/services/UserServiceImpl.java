package userService.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import userService.DTOs.UserDTO;
import userService.exceptions.UserAlreadyExistException;
import userService.models.User;
import userService.models.VerificationToken;
import userService.repositories.UserRepository;
import userService.repositories.VerificationTokenRepository;
import userService.security.OnRegistrationCompleteEvent;
import userService.utils.GenericResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by DPLICHTA on 5/15/2017.
 */
@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Autowired
    private VerificationTokenRepository verificationTokenRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messages;

    @Override
    public GenericResponse registerNewUserAccount(String userName, String password, String matchingPassword, String email, boolean isUsing2FA, HttpServletRequest request) throws UserAlreadyExistException {
        System.out.println("grrrr");
        UserDTO userDto = new UserDTO(userName, password, matchingPassword, email, isUsing2FA);

        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + userDto.getEmail());
        }
        if (loginExist(userDto.getUserName())) {
            throw new UserAlreadyExistException("There is an account with that username: " + userDto.getUserName());
        }
        final User user = new User();

        user.setFirstName(userDto.getUserName());
        user.setLogin(userDto.getUserName());
        System.out.println(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        //user.setEnabled(true);
        //user.setUsing2FA(accountDto.isUsing2FA());
        //user.setRoles(Arrays.asList(roleRepository.findByName("USER_ROLE")));
        User registered = repository.save(user);
//        repository.save(user);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));

        return new GenericResponse("success");
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }

    public GenericResponse confirmRegistration
            (Locale locale, String token) {
//            (WebRequest request, Model model, @RequestParam("token") String token) {
//        Locale locale = request.getLocale();

        VerificationToken verificationToken = getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            return new GenericResponse(message);
            //model.addAttribute("message", message);
            //  return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        System.out.println(verificationToken);
        System.out.println(verificationToken.getExpiryDate());


        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            return new GenericResponse(messageValue);
            // return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }
        System.out.println("zzzzz");
        System.out.println(user.getEmail());
        user.setEnabled(true);
        repository.save(user);
        return new GenericResponse("success");
        //  return new ModelAndView("redirect:" + "http://127.0.0.1:9000/#!/emailConfirmed");
    }

    @Override
    public String searchFriends(String text) {
        System.out.println("hereCarrs");
        return text;
    }

    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
    /**
     * Global instance of the HTTP transport.
     */

    /**
     * Global instance of the JSON factory.
     */

    private boolean emailExist(final String email) {
        return repository.findByEmail(email) != null;
    }

    private boolean loginExist(final String login) {
        return repository.findByLogin(login) != null;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}



