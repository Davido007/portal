package io.pivotal.microservices.Security;

import io.pivotal.microservices.accounts.SecurityUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DPLICHTA on 5/8/2017.
 */
@Component("restAuthenticationFailureHandler")
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        System.out.println("kkkkkk");
        SecurityUtils.sendError(response, exception, HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }
}
