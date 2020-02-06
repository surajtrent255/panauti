/**
 * @author Umesh Bhujel <yoomesbhujel@gmail.com>
 * Since Dec 11, 2019
 */
package com.ishanitech.ipalika.config;



import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfig {
	@Primary
	@Bean
	HikariDataSource datasource() {
		HikariConfig config = new HikariConfig();
		config.setUsername("root");
<<<<<<< HEAD
		config.setPassword("root");
=======
		config.setPassword("24Pr1y@msh@");
>>>>>>> ba1db60cf5a9fe9e859871b64bdfcb425250a279
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:3306/ipalika?autoReconnect=true&serverTimezone=UTC");
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
		jdbi.installPlugin(new SqlObjectPlugin());
		return jdbi;
	}
}