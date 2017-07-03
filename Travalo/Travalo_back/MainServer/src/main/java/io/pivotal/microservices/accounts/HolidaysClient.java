package io.pivotal.microservices.accounts;

import com.travalo.holidayservice.services.HolidaysService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by DPLICHTA on 6/14/2017.
 */
@FeignClient("holidays-service")
public interface HolidaysClient extends HolidaysService {
}
