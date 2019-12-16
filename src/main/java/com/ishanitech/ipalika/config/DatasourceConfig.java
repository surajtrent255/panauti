/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 11, 2019
 */
package com.ishanitech.ipalika.config;


import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.mapper.RowMapperFactory;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

import com.ishanitech.ipalika.model.Role;
import com.ishanitech.ipalika.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfig {
	@Primary
	@Bean
	HikariDataSource datasource() {
		HikariConfig config = new HikariConfig();
		config.setUsername("root");
		config.setPassword("password");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:3306/IPALIKA?autoReconnect=true&serverTimezone=UTC");
		return new HikariDataSource(config);
	}
	
	@Bean
	TransactionAwareDataSourceProxy transactionAwareDataSourceProxy() {
		TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(datasource());
		return proxy;
	}
	
	@Bean
	PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(transactionAwareDataSourceProxy());
	}
	
	@Bean
	Jdbi jdbiBean() {
		Jdbi jdbi = Jdbi.create(transactionAwareDataSourceProxy());
		jdbi.registerRowMapper(BeanMapper.factory(User.class));
		jdbi.registerRowMapper(BeanMapper.factory(Role.class));
		jdbi.installPlugin(new SqlObjectPlugin());
		return jdbi;
	}
}