package io.pivotal.microservices.accounts;

import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final userService.models.User user;

    public OnRegistrationCompleteEvent(final userService.models.User user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    //

    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public userService.models.User getUser() {
        return user;
    }

}