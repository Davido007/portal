package userService.DTOs;

/**
 * Created by DPLICHTA on 7/12/2017.
 */

import org.hibernate.validator.constraints.NotEmpty;
import userService.validations.PasswordMatches;
import userService.validations.ValidEmail;

import javax.validation.constraints.NotNull;

/**
 * Created by DPLICHTA on 4/12/2017.
 */
@PasswordMatches
public class UserDTO {
    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    private boolean isUsing2FA;

    public UserDTO() {
    }

    public UserDTO(String userName, String password, String matchingPassword, String email, boolean isUsing2FA) {
        this.userName = userName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.isUsing2FA = isUsing2FA;
    }

    // standard getters and setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", email='" + email + '\'' +
                ", isUsing2FA=" + isUsing2FA +
                '}';
    }
}
