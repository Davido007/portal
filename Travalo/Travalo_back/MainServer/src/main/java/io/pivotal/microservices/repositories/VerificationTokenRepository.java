package io.pivotal.microservices.repositories;

import io.pivotal.microservices.accounts.User;
import io.pivotal.microservices.accounts.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by DPLICHTA on 5/11/2017.
 */
public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}