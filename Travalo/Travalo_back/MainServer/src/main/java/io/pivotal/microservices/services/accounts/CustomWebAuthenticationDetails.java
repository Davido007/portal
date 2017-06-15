package io.pivotal.microservices.services.accounts;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by DPLICHTA on 4/14/2017.
 */

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private static final long serialVersionUID = 1L;

    private final String verificationCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verificationCode = request.getParameter("code");
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}