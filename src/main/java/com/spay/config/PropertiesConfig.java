package com.spay.config;

import java.io.IOException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.spay.core.constants.SystemConstants;
import com.spay.utils.ResourceUtils;

@Configuration
public class PropertiesConfig {
	static final Logger logger = LoggerFactory.getLogger(PropertiesConfig.class);

    @Bean
    public static EnvironmentStringPBEConfig environmentVariableConfiguration() {
        EnvironmentStringPBEConfig envConfig = new EnvironmentStringPBEConfig();
        envConfig.setAlgorithm("PBEWithMD5AndDES");
        envConfig.setPassword(SystemConstants.SPAY_ENC_KEY);
        envConfig.setPassword("ECS_PROJECT_PRIVATE_KEY");

        return envConfig;
    }

    @Bean
    public static StandardPBEStringEncryptor configurationEncryptor() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setConfig(environmentVariableConfiguration());

        return standardPBEStringEncryptor;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
    	
    	PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    	/*
    	EncryptablePropertyPlaceholderConfigurer configurer = new EncryptablePropertyPlaceholderConfigurer(
            configurationEncryptor());
        */
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] locations = patternResolver.getResources("classpath*:config/*.config.xml");
        
        logger.info("Resource Info ------------------------------------------");
        for (Resource resource : locations) {
        	logger.info(resource.getFilename());
		}
        logger.info("Resource Info ------------------------------------------");
        
        configurer.setLocations(ResourceUtils.getDistinctResources(locations));
        configurer.setIgnoreUnresolvablePlaceholders(true);
        configurer.setIgnoreResourceNotFound(true);

        return configurer;
    }

}
