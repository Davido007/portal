package io.pivotal.microservices.accounts;

import com.travalo.guideservice.services.GuideService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by DPLICHTA on 5/16/2017.
 */
@FeignClient("guide-service")
public interface GuideClient extends GuideService {
}
