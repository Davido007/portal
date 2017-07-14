package friendsService.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by DPLICHTA on 4/12/2017.
 */

public interface FriendsService {

    @RequestMapping(method = RequestMethod.GET, value = "/searchFriends")
    public String searchFriends(@RequestParam("text") String text);

}