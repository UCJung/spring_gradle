package com.spay.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * Database & Connection Pool(HikariCP) Configuration
 * @author mykumi
 *
 */
@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY, order = 0)
@MapperScan({"com.spay.module"})
@Import(PropertiesConfig.class)
public class DataConfig {
	static final Logger logger = LoggerFactory.getLogger(DataConfig.class);	
	
	/**
	 * config properties of resource/config/data.config.xml
	 */
    private @Value("${jdbc.minimumIdle}") int minimumIdle;

    private @Value("${jdbc.maximumPoolSize}") int maximumPoolSize;
    private @Value("${jdbc.validationQuery}") String validationQuery;
    private @Value("${jdbc.connectionTimeout}") int connectionTimeout;
    private @Value("${jdbc.autocommit}") boolean isAutoCommit;

    private @Value("${datasource.dataUrl}") String dataUrl;
    private @Value("${datasource.user}") String user;
    private @Value("${datasource.password}") String password;

    private @Value("${datasource.driverClassName}") String driverClassName;
    private @Value("${datasource.cachePrepStmts}") boolean cachePrepStmts;
    private @Value("${datasource.prepStmtCacheSize}") int prepStmtCacheSize;
    private @Value("${datasource.prepStmtCacheSqlLimit}") int prepStmtCacheSqlLimit;
    private @Value("${datasource.useServerPrepStmts}") boolean useServerPrepStmts;
	
	/**
	 * DataSource Configuration for HikariCP
	 * @return
	 */
	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		
        HikariConfig config = new HikariConfig();

        config.setMinimumIdle(minimumIdle);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setConnectionTestQuery(validationQuery);
        config.setConnectionTimeout(connectionTimeout);
        config.setAutoCommit(isAutoCommit);

        config.addDataSourceProperty("cachePrepStmts", cachePrepStmts);
        config.addDataSourceProperty("prepStmtCacheSize", prepStmtCacheSize);
        config.addDataSourceProperty("useServerPrepStmts", useServerPrepStmts);
		
        config.addDataSourceProperty("user", user);
        config.addDataSourceProperty("password", password);
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(dataUrl);

        
        return new HikariDataSource(config);		
   	}
	
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactoryBean sessionFactoryBean() throws IOException {
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*.xml"));
		
		return sessionFactory;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}	
}
