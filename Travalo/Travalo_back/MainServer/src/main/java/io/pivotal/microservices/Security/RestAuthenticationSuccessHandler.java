package io.pivotal.microservices.Security;

import io.pivotal.microservices.accounts.SecurityUtils;
import io.pivotal.microservices.accounts.User;
import io.pivotal.microservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DPLICHTA on 5/8/2017.
 */
@Component
@ComponentScan(basePackages = {"io.pivotal.microservices.repositories"})
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
        System.out.println("cccccccccccc");
        User user = userService.findByLogin(authentication.getName());
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, user);
    }
}
