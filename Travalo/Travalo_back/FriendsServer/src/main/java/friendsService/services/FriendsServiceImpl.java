package friendsService.services;


import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DPLICHTA on 5/15/2017.
 */
@RestController
public class FriendsServiceImpl implements FriendsService {



    private static final String APPLICATION_NAME = "MyFlightApplication";

    private static final String API_KEY = "AIzaSyCJZzEKjIHNWpwPPxFrkcbxUcvPlbXaGVA";

    private static final String CITY_ICON="https://www.tripexpert.com/assets/tripexpert/pin@2x-231f842561316cf6f01ac691edc079f81f46e701ec59cf1dc470ff773635f3c9.png";

    @Override
    public String searchFriends(String text) {
        System.out.println("hereCarrs");
        return text;
    }

    /**
     * Global instance of the HTTP transport.
     */

    /**
     * Global instance of the JSON factory.
     */


}



