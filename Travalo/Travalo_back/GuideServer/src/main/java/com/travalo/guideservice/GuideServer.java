package com.travalo.guideservice;

import com.travalo.guideservice.configuration.HotelsConfiguration;
import com.travalo.guideservice.services.GuideService;
import com.travalo.guideservice.services.GuideServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * Accounts web-server. Works as a microservice client, fetching data from the
 * Account-Service. Uses the Discovery Server (Eureka) to find the microservice.
 * 
 * @author Paul Chapman
 */
@SpringBootApplication
@EnableDiscoveryClient
@Import(HotelsConfiguration.class)
//@ComponentScan(useDefaultFilters = false) // Disable component scanner
public class GuideServer {
	public static final String USER_SERVICE_URL = "http://USER-SERVICE";
	/**
	 * URL uses the logical name of account-service - upper or lower case,
	 * doesn't matter.
	 */
	public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-SERVICE";

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or guide-server.yml
		System.setProperty("spring.config.name", "guide-server");
		SpringApplication.run(GuideServer.class, args);
	}

	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * Note that prior to the "Brixton" 
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public GuideService guideServiceImpl() {
		return new GuideServiceImpl();
	}
/*	@Bean
	public WebAccountsService accountsService() {
		return new WebAccountsService(ACCOUNTS_SERVICE_URL);
	}*/
/*	@Bean
	public WebAccountsService accountsService() {
		return new WebAccountsService(ACCOUNTS_SERVICE_URL);
	}

	*//**
	 * Create the controller, passing it the {@link WebAccountsService} to use.
	 * 
	 * @return
	 *//*


	@Bean
	public HomeController homeController() {
		return new HomeController();
	}*/
}
