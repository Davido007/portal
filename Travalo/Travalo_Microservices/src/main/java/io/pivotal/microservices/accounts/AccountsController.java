package io.pivotal.microservices.accounts;

import io.pivotal.microservices.exceptions.AccountNotFoundException;
import io.pivotal.microservices.repositories.UserRepository;
import io.pivotal.microservices.services.user.IUserService;
import io.pivotal.microservices.users.UserDTO;
import io.pivotal.microservices.utils.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * A RESTFul controller for accessing account information.
 * 
 * @author Paul Chapman
 */
@CrossOrigin(origins="http://127.0.0.1:9000")
@RestController
public class AccountsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	IUserService service;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	protected UserRepository userRepository;

	/**
	 * Create an instance plugging in the respository of Accounts.
	 * 
	 * @param userRepository
	 *            An account repository implementation.
	 */
/*	@Autowired
	public AccountsController(UserRepository userRepository) {
		this.userRepository = userRepository;

	}*/

	/**
	 * Fetch an account with the specified account number.
	 * 
	 * @param accountNumber
	 *            A numeric, 9 digit account number.
	 * @return The account if found.
	 * @throws AccountNotFoundException
	 *             If the number is not recognised.
	 */
/*	@RequestMapping("/accounts/{accountNumber}")
	public Account byNumber(@PathVariable("accountNumber") String accountNumber) {

		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		Account account = accountRepository.findByNumber(accountNumber);
		logger.info("accounts-service byNumber() found: " + account);

		if (account == null)
			throw new AccountNotFoundException(accountNumber);
		else {
			return account;
		}
	}*/

	/**
	 * Fetch accounts with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../accounts/owner/a</code> will find any
	 * accounts with upper or lower case 'a' in their name.
	 * 
	 * @param
	 * @return A non-null, non-empty set of accounts.
	 * @throws AccountNotFoundException
	 *             If there are no matches at all.
	 */
/*	@RequestMapping("/accounts/owner/{name}")
	public List<Account> byOwner(@PathVariable("name") String partialName) {
		logger.info("accounts-service byOwner() invoked: "
				+ accountRepository.getClass().getName() + " for "
				+ partialName);

		List<Account> accounts = accountRepository
				.findByOwnerContainingIgnoreCase(partialName);
		logger.info("accounts-service byOwner() found: " + accounts);

		if (accounts == null || accounts.size() == 0)
			throw new AccountNotFoundException(partialName);
		else {
			return accounts;
		}
	}*/
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginPage(ModelMap model) {
		System.out.println("aaaa");
		return "{\"login\":\"login\"}";
	}
	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse registerUserAccount(@RequestBody @Valid final UserDTO accountDto, final HttpServletRequest request) {
		LOGGER.debug("Registering user account with information: {}", accountDto);
		final User registered = service.registerNewUserAccount(accountDto);
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
		return new GenericResponse("success");
	}
/*	private User createUserAccount(UserDTO accountDto, BindingResult result) {
		User registered = null;
		try {
			registered = service.registerNewUserAccount(accountDto);
		} catch (EmailExistsException e) {
			return null;
		}
		return registered;
	}*/
	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
