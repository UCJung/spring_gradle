package com.spay.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PropertiesConfig.class, DataConfig.class } )
@ComponentScan( 
		basePackages={ "com.spay.web.config" } )
public class AppConfig {

}
