package io.pivotal.microservices.Security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by DPLICHTA on 5/8/2017.
 */
public class UserNotEnabledException extends AuthenticationException {

    private static final long serialVersionUID = -391087554279066060L;

    public UserNotEnabledException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotEnabledException(String msg) {
        super(msg);
    }
}