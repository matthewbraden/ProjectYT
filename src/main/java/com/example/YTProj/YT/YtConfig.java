package com.example.YTProj.YT;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import com.example.YTProj.YT.Model.*;
import com.example.YTProj.YT.Repository.*;


@Configuration
@EnableJpaRepositories(
	entityManagerFactoryRef = "db1EntityMgrFactory",          
	transactionManagerRef = "db1TransactionMgr", 
	basePackageClasses = YtRepository.class
)
@EnableTransactionManagement
public class YtConfig {
	
	@Autowired
	private Environment env;

	@Bean(name="datasource1")
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		return dataSource;
	}
	
	@Bean(name = "db1EntityMgrFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean db1EntityMgrFactory(
	   EntityManagerFactoryBuilder builder,
	   @Qualifier("datasource1") DataSource dataSource) {
	  return builder
	    .dataSource(dataSource)
	    .packages(Yt.class)
	    .persistenceUnit("queuea")
	    .build();
	 }
	
	@Bean(name = "db1TransactionMgr")
	@Primary
	public PlatformTransactionManager db1TransactionMgr(
	   @Qualifier("db1EntityMgrFactory") EntityManagerFactory entityManagerFactory) {
	  return new JpaTransactionManager(entityManagerFactory);
	}
}
