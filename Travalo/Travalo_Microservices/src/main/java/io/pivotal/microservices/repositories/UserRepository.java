package io.pivotal.microservices.repositories;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

import io.pivotal.microservices.accounts.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}