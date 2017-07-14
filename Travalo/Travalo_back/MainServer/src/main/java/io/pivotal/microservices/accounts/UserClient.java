package io.pivotal.microservices.accounts;

/**
 * Created by DPLICHTA on 7/12/2017.
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import userService.services.UserService;

/**
 * Created by DPLICHTA on 6/14/2017.
 */
@FeignClient("user-service")
public interface UserClient extends UserService {
}
