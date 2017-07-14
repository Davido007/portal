package userService.utils;

/**
 * Created by DPLICHTA on 5/8/2017.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import userService.models.User;
import userService.repositories.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    @Autowired
   static  UserRepository userRepository;


    private static final ObjectMapper mapper = new ObjectMapper();


    private SecurityUtils() {
    }


    /**
     * Get the login of the current user.
     */
    public static User getCurrentLogin() {
        System.out.println("OOOOO");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails springSecurityUser = null;
        String userName = null;
        if(authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                System.out.println("kkk"+authentication.getPrincipal());
                springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                System.out.println("kkk1"+authentication.getPrincipal());
                userName = (String) authentication.getPrincipal();
            }
        }
        System.out.println("zzz"+ userName);
        //findUser(userName);
        System.out.println(userName);
        return findUser(userName);
       // return userRepository.findByLogin(userName);
    }
    public static User findUser(String userName){
        return userRepository.findByLogin(userName);
    }



    public static void sendError(HttpServletResponse response, Exception exception, int status, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        PrintWriter writer = response.getWriter();
        Error error = new Error("authError", exception.getMessage());
        writer.write(mapper.writeValueAsString(new Response(status, message, error)));
        writer.flush();
        writer.close();
    }


    public static void sendResponse(HttpServletResponse response, int status, Object object) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(object));
        response.setStatus(status);
        writer.flush();
        writer.close();
    }

}
