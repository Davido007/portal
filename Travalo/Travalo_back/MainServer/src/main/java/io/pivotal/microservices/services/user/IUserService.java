package io.pivotal.microservices.services.user;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

import io.pivotal.microservices.accounts.User;
import io.pivotal.microservices.accounts.VerificationToken;
import io.pivotal.microservices.exceptions.UserAlreadyExistException;
import io.pivotal.microservices.users.UserDTO;


public interface IUserService {

    User registerNewUserAccount(UserDTO accountDto) throws UserAlreadyExistException;

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    void saveRegisteredUser(User user);
/*
    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    User getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    User updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();*/

}