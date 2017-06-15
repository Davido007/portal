package io.pivotal.microservices.repositories;

import io.pivotal.microservices.accounts.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by DPLICHTA on 5/8/2017.
 */
public interface TokenRepo extends JpaRepository<Token, String> {
}
