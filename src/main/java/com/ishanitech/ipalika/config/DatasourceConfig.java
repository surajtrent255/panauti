/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 11, 2019
 */
package com.ishanitech.ipalika.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfig {
	@Bean
	HikariDataSource datasource() {
		HikariConfig config = new HikariConfig();
		config.setUsername("root");
		config.setPassword("password");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:3306/IPALIKA?autoReconnect=true&serverTimezone=UTC");
		return new HikariDataSource(config);
	}
}