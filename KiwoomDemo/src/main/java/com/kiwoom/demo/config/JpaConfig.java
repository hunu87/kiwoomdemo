package com.kiwoom.demo.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties
@EnableJpaRepositories(
		basePackages={"com.kiwoom.demo.account.repository", "com.kiwoom.demo.test.repository"},  //repository를 관리할 패키지 명시
	    entityManagerFactoryRef = "entityManagerFactory", //EntityManagerFactory
	    transactionManagerRef = "transactionManager") // transactionManager

public class JpaConfig {
	
	@Value("${spring.datasource.driver-class-name}")
    private String driveClassName;

	@Value("${spring.datasource.url}")
    private String driverUrl;

	@Value("${spring.datasource.username}")
    private String driveUsername;

	@Value("${spring.datasource.password}")
    private String drivePassword;
	
	private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy";
	
	@Primary //해당 Bean을 우선적으로 선택하도록 하는 annotation
	@Bean
	public DataSource defaultDataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();
			
		dataSourceConfig.setDriverClassName(driveClassName);
	    dataSourceConfig.setJdbcUrl(driverUrl);
	    dataSourceConfig.setUsername(driveUsername);
	    dataSourceConfig.setPassword(drivePassword);
		
	    return new HikariDataSource(dataSourceConfig);
	}
	
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
	
		Map<String, String> propertiesHashMap = new HashMap<>();
		propertiesHashMap.put("hibernate.hbm2ddl.auto", "validate");
		propertiesHashMap.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		propertiesHashMap.put("hibernate.show_sql", "true");
		propertiesHashMap.put("hibernate.format_sql", "true");
	  	propertiesHashMap.put("hibernate.physical_naming_strategy", DEFAULT_NAMING_STRATEGY);

	  	LocalContainerEntityManagerFactoryBean rep = builder.dataSource(defaultDataSource())
	  			.packages("com.kiwoom.demo.account.vo", "com.kiwoom.demo.test.entity") //domain을 관리할 패키지 경로 명시 (domain = VO 파일)
	  			.properties(propertiesHashMap)
	  			.build();
	 
	  	return rep;
	}

	@Primary
	@Bean(name = "transactionManager")
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}
}
