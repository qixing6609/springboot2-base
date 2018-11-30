package com.ts.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
@MapperScan(basePackages = { PrimaryDataSourceConfig.SECURITY_PACKAGE},
			sqlSessionFactoryRef = "YdjDbSqlSessionFactory")
public class PrimaryDataSourceConfig {
	static final String SECURITY_PACKAGE = "com.ydj.service.mapper";

	@Autowired
	private Environment env;

	@Bean(name = "YdjDbDataSource")
	@Primary
	public DataSource ydjDbDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("ydj.driverClassName"));
		dataSource.setUrl(env.getProperty("ydj.url"));
		dataSource.setUsername(env.getProperty("ydj.username"));
		dataSource.setPassword(env.getProperty("ydj.password"));
		dataSource.setMinIdle(env.getProperty("ydj.minIdle", Integer.class));
		dataSource
				.setMaxActive(env.getProperty("ydj.maxActive", Integer.class));
		dataSource.setInitialSize(
				env.getProperty("ydj.initialSize", Integer.class));
		dataSource.setTimeBetweenEvictionRunsMillis(env
				.getProperty("ydj.timeBetweenEvictionRunsMillis", Long.class));
		dataSource.setMinEvictableIdleTimeMillis(
				env.getProperty("ydj.minEvictableIdleTimeMillis", Long.class));
		dataSource.setValidationQuery(env.getProperty("ydj.validationQuery"));
		dataSource.setTestWhileIdle(
				env.getProperty("ydj.testWhileIdle", Boolean.class));
		dataSource.setTestOnBorrow(
				env.getProperty("ydj.testOnBorrow", Boolean.class));
		dataSource.setTestOnReturn(
				env.getProperty("ydj.testOnReturn", Boolean.class));

		return dataSource;
	}

	@Bean(name = "YdjDbTransactionManager")
	@Primary
	public DataSourceTransactionManager ydjDbTransactionManager() {
		return new DataSourceTransactionManager(ydjDbDataSource());
	}

	@Bean(name = "YdjDbSqlSessionFactory")
	@Primary
	public SqlSessionFactory ydjDbSqlSessionFactory(
			@Qualifier("YdjDbDataSource") DataSource dataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		return sessionFactoryBean.getObject();
	}
}
