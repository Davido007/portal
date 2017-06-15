package io.pivotal.microservices.users;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

import io.pivotal.microservices.validations.ValidEmail;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by DPLICHTA on 4/12/2017.
 */
public class UserDto2 {
    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    private boolean isUsing2FA;

    @JsonCreator
    public UserDto2(@JsonProperty("username") String username, @JsonProperty("password") String password,
                    @JsonProperty("matchingPassword") String matchingPassword,
                    @JsonProperty("email") String email
                    ) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
    }

    public UserDto2() {
    }
    // standard getters and setters

    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean using2FA) {
        isUsing2FA = using2FA;
    }

    @Override
    public String toString() {
        return "UserDto2{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", email='" + email + '\'' +
                ", isUsing2FA=" + isUsing2FA +
                '}';
    }
}
