package io.pivotal.microservices.services.accounts;

import io.pivotal.microservices.Security.ActiveUserStore;
import io.pivotal.microservices.accounts.AccountsConfiguration;
import io.pivotal.microservices.services.flights.FlightsService;
import io.pivotal.microservices.services.flights.FlightsServiceImpl;
import io.pivotal.microservices.services.user.IUserService;
import io.pivotal.microservices.services.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link AccountsConfiguration}. This is a deliberate separation of concerns.
 *
 * @author Paul Chapman
 */
@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@Import(AccountsConfiguration.class)
public class AccountsServer {

/*    @Autowired
    protected UserRepository userRepository;*/

/*    @Autowired
    private MyUserDetailsService userDetailsService;*/

    protected Logger logger = Logger.getLogger(AccountsServer.class.getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     * Program arguments - ignored.
     */
    public static final String USER_SERVICE_URL = "http://USER-SERVICE";

    public static void main(String[] args) {
        // Tell server to look for accounts-server.properties or
        // accounts-server.yml
        System.setProperty("spring.config.name", "accounts-server");

        SpringApplication.run(AccountsServer.class, args);
    }

    @Bean
    public IUserService accountsService() {
        return new UserService(USER_SERVICE_URL);
    }
    @Bean
    public FlightsService flightsService() {
        return new FlightsServiceImpl();
    }

    @Bean
    public ActiveUserStore activeUserStore() {
        return new ActiveUserStore();
    }

    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return cookieLocaleResolver;
    }


/*    @Bean
    DataSource dataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser("Travalo");
        dataSource.setPassword("xxqi1nkv");
        dataSource.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }*/

}
