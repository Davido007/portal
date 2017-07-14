package userService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import userService.models.User;
import userService.models.VerificationToken;

/**
 * Created by DPLICHTA on 5/11/2017.
 */
public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}