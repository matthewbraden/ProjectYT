package com.example.YTProj.YT2;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.YTProj.YT2.Model.Yt2;
import com.example.YTProj.YT2.Repository.Yt2Repository;


@Configuration
@EnableJpaRepositories(
entityManagerFactoryRef = "db2EntityMgrFactory", 
transactionManagerRef = "db2TransactionMgr", 
basePackageClasses = Yt2Repository.class
)
@EnableTransactionManagement
public class Yt2Config {
		
	@Autowired
	private Environment env;

	@Bean(name="datasource2")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("spring.second-datasource.url"));
		return dataSource;
	}
	
	@Bean(name = "db2EntityMgrFactory")
	public LocalContainerEntityManagerFactoryBean db2EntityMgrFactory (
			EntityManagerFactoryBuilder builder,
			@Qualifier("datasource2") DataSource dataSource) {
		return builder
		    .dataSource(dataSource)
		    .packages(Yt2.class)
		    .persistenceUnit("queueb")
		    .build();
	 }

	@Bean(name = "db2TransactionMgr")
	 public PlatformTransactionManager db2TransactionMgr(
	   @Qualifier("db2EntityMgrFactory") EntityManagerFactory entityManagerFactory) {
	  return new JpaTransactionManager(entityManagerFactory);
	 }


}
