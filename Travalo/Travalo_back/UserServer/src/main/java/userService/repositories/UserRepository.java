package userService.repositories;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userService.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByLogin(String login);

    List<User> findByLoginStartingWith(String login);
    //User findByUserName(String userName);

    @Override
    void delete(User user);

}