package com.demo.appname.api.common.config.database;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.demo.appname.api.common.constant.DataSourceType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = { "com.demo.appname.api.domain" }, sqlSessionFactoryRef = "sqlSessionFactory", annotationClass = SampleMapper.class)
public class DatabaseConfig {
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.sampledb.master.hikari")
	public HikariConfig masterHikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource masterDataSource() {
		return new HikariDataSource(masterHikariConfig());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.sampledb.slave.hikari")
	public HikariConfig slaveHikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource slaveDataSource() {
		return new HikariDataSource(slaveHikariConfig());
	}
	
	@Bean
	public DataSource routeDataSource() {
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(DataSourceType.MASTER, masterDataSource());
		dataSourceMap.put(DataSourceType.SLAVE, slaveDataSource());
		
		RoutingDataSource routingDataSource = new RoutingDataSource();
		routingDataSource.setDefaultTargetDataSource(masterDataSource());
		routingDataSource.setTargetDataSources(dataSourceMap);
		
		return routingDataSource;
	}
	
	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		return new LazyConnectionDataSourceProxy(routeDataSource());
	}
	
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "sqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean(name = "transactionManager")
	@Primary
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(routeDataSource());
	}

}
