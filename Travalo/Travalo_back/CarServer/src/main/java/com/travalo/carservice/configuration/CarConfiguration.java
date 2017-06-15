package com.travalo.carservice.configuration;

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
 * @author Paul Chapman
 */
@Configuration
@ComponentScan
//@EntityScan("io.pivotal.microservices.accounts")
@EnableJpaRepositories("com.travalo.carservice.repositories")
@PropertySource("classpath:db-config.properties")
public class CarConfiguration {

	protected Logger logger;

	public CarConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory "rewards" database populated with test data for fast
	 * testing
	 */
/*	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/data.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> accounts = jdbcTemplate.queryForList("SELECT number FROM T_ACCOUNT");
		logger.info("System has " + accounts.size() + " accounts");

		// Populate with random balances
		Random rand = new Random();

		for (Map<String, Object> item : accounts) {
			String number = (String) item.get("number");
			BigDecimal balance = new BigDecimal(rand.nextInt(10000000) / 100.0).setScale(2, BigDecimal.ROUND_HALF_UP);
			jdbcTemplate.update("UPDATE T_ACCOUNT SET balance = ? WHERE number = ?", balance, number);
		}

		return dataSource;
	}*/
	@Bean
	@ConfigurationProperties("spring.datasource")
	DataSource dataSource() throws SQLException {
	/*	OracleDataSource dataSource = new OracleDataSource();
		dataSource.setUser("Travalo");
		dataSource.setPassword("xxqi1nkv");
		dataSource.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
		dataSource.setImplicitCachingEnabled(true);
		dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;*/

		return DataSourceBuilder.create().build();
	}
}