package com.cg.multiple.configurations;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.cg.multiple.repository.bank" , 
entityManagerFactoryRef =  "bankEntityManagerFactory" , 
transactionManagerRef = "bankTransactionManager" )
public class BankDataSourceConfiguration {
	
	
	@Bean
	@ConfigurationProperties("spring.datasource.bank")
	public DataSourceProperties dataSourceProperties() {
		
		return new DataSourceProperties();
	}
	
	
	@Bean
	public DataSource dataSource() {
		
		return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	
	@Bean(name = "bankEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean bankEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		
		
	   return builder.dataSource(this.dataSource()).packages("com.cg.multiple.model.bank").build();
		
	}
	
	
	@Bean(name = "bankTransactionManager")
	public PlatformTransactionManager transactionManager( @Qualifier("bankEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}
	
	
	
	
	
	

}
