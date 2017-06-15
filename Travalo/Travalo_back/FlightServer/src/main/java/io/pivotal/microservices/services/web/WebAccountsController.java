package io.pivotal.microservices.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Client controller, fetches Account info from the microservice via
 * {@link }.
 *
 * @author Paul Chapman
 */
@CrossOrigin(origins = "http://127.0.0.1:9000")
@RestController
public class WebAccountsController {

    @Autowired
    protected FlightService accountsService;

    protected Logger logger = Logger.getLogger(WebAccountsController.class
            .getName());



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("accountNumber", "searchText");
    }

    @RequestMapping("/accounts")
    public String goHome() {
        return "index";
    }


    @RequestMapping("/accounts/{accountNumber}")
    public String byNumber(Model model,
                           @PathVariable("accountNumber") String accountNumber) {

        logger.info("web-service byNumber() invoked: " + accountNumber);

        return "account";
    }

    @RequestMapping("/accounts/owner/{text}")
    public String ownerSearch(Model model, @PathVariable("text") String name) {
        logger.info("web-service byOwner() invoked: " + name);


        return "accounts";
    }

    @RequestMapping(value = "/accounts/search", method = RequestMethod.GET)
    public String searchForm(Model model) {
        model.addAttribute("searchCriteria");
        return "accountSearch";
    }

    @RequestMapping(value = "/accounts/dosearch")
    public String doSearch(Model model,
                           BindingResult result) {
        logger.info("web-service search() invoked: ");

        return ownerSearch(model, "a");
    }
}

