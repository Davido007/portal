package io.pivotal.microservices.repositories;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

import io.pivotal.microservices.accounts.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}