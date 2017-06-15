package io.pivotal.microservices.repositories;

import io.pivotal.microservices.accounts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DPLICHTA on 5/8/2017.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);

}
