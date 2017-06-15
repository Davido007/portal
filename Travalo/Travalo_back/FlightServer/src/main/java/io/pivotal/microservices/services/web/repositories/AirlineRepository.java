package io.pivotal.microservices.services.web.repositories;

import io.pivotal.microservices.services.web.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by DPLICHTA on 6/13/2017.
 */
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    List<Airline> findAllByIata(String iata);

}
