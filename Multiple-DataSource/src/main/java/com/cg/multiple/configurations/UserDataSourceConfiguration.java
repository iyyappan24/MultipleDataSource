package com.cg.multiple.configurations;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.cg.multiple.repository.user",
entityManagerFactoryRef = "userEntityManagerFactory",
transactionManagerRef = "userTransactionManager"
		)
public class UserDataSourceConfiguration {

	
	
	@Bean(name = "userDataSourceProperties")
	@Primary
	@ConfigurationProperties("spring.datasource.user")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	
	@Bean(name = "userDataSource")
	@Primary
	public DataSource dataSource() {
		
		return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	
	@Bean(name = "userEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		
		return builder.dataSource(dataSource()).packages("com.cg.multiple.model.user").build();
	}
	
	
	@Bean(name = "userTransactionManager")
	@Primary
	public PlatformTransactionManager transactionManager( @Qualifier("userEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory){
		
		return new JpaTransactionManager(entityManagerFactory.getObject());
		
	}
		
	}
	
	
	

