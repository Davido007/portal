package io.pivotal.microservices.accounts;

import io.pivotal.microservices.services.web.FlightService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by DPLICHTA on 5/16/2017.
 */
@FeignClient("web-service")
public interface FlightsClient extends FlightService {
}
