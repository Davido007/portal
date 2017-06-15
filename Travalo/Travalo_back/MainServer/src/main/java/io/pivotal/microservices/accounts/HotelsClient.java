package io.pivotal.microservices.accounts;

import com.travalo.hotelService.services.HotelService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by DPLICHTA on 5/16/2017.
 */
@FeignClient("hotel-service")
public interface HotelsClient extends HotelService {
}
