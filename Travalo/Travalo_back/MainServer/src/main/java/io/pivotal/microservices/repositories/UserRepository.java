package io.pivotal.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DPLICHTA on 7/14/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<io.pivotal.microservices.accounts.User, Long> {
    io.pivotal.microservices.accounts.User findByEmail(String email);
    io.pivotal.microservices.accounts.User findByLogin(String login);

    List<io.pivotal.microservices.accounts.User> findByLoginStartingWith(String login);
    //User findByUserName(String userName);

    @Override
    void delete(io.pivotal.microservices.accounts.User user);

}