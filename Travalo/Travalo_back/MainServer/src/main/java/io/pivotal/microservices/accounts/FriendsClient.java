package io.pivotal.microservices.accounts;

/**
 * Created by DPLICHTA on 7/11/2017.
 */

import friendsService.services.FriendsService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("friends-service")
public interface FriendsClient extends FriendsService {
}
