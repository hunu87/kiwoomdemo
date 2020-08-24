package com.kiwoom.demo.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.kiwoom.demo.test",
		sqlSessionFactoryRef = "sqlSessionFactory",
		sqlSessionTemplateRef = "sqlSession")

public class MybatisConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
    public SqlSessionFactory sqlSessionFactory (DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.kiwoom.demo.test");
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/**/*.xml"));
		
		return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSession (SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
