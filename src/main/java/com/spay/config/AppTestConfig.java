package com.spay.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan( basePackages={ "com.spay" } , 
includeFilters={ @ComponentScan.Filter(Controller.class)},
excludeFilters={ 
		@ComponentScan.Filter(Configuration.class)})
@Import({ PropertiesConfig.class, DataConfig.class })
public class AppTestConfig {

}
