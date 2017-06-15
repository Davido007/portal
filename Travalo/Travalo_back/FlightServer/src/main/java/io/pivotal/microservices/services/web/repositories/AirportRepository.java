package io.pivotal.microservices.services.web.repositories;

import io.pivotal.microservices.services.web.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by DPLICHTA on 6/12/2017.
 */
public interface AirportRepository extends JpaRepository<Airport, Long> {

    //Airport findAllByText(String name);

    List<Airport> findByNameStartingWith(String name);

    List<Airport> findByIataStartingWith(String iata);

}