package userService.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * The accounts Spring configuration.
 * 
 * @author Dawid Plichta
 */
@Configuration
@ComponentScan
@EnableJpaRepositories("userService.repositories")
@PropertySource("classpath:db-config.properties")
public class FriendsConfiguration {

	protected Logger logger;

	public FriendsConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	@Bean
	@ConfigurationProperties("spring.datasource")
	DataSource dataSource() throws SQLException {

		return DataSourceBuilder.create().build();
	}
}