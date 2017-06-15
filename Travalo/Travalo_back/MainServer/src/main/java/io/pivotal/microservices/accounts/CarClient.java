package io.pivotal.microservices.accounts;

import com.travalo.carservice.CarService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by DPLICHTA on 6/14/2017.
 */
@FeignClient("car-service")
public interface CarClient extends CarService {
}
