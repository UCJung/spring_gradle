package com.spay.web.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.scene.chart.PieChart.Data;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * Database Configuration
 * @author mykumi
 *
 */
@Configuration
@MapperScan("com.spay.mapper")
public class DataConfig {
	/**
	 * DataSource Configuration for HikariCP
	 * @return
	 */
	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		
        dataSource.setUsername("dbuser");
        dataSource.setPassword("dbuser1*");

        dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        dataSource.addDataSourceProperty("url", "jdbc:mysql://54.64.47.206/mykumidb");

        dataSource.setMinimumIdle(1);
        dataSource.setMaximumPoolSize(10);
        
        return dataSource;		
   	}
	
	@Bean(destroyMethod="shutdown")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean(destroyMethod="shutdown")
	public SqlSessionFactoryBean sessionFactoryBean() throws IOException {
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		
		return sessionFactory;
	}
}