package userService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import userService.models.Token;

/**
 * Created by DPLICHTA on 5/8/2017.
 */
public interface TokenRepository extends JpaRepository<Token, String> {
}
