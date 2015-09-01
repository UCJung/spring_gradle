package com.spay.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan( basePackages={ "com.spay" } , 
includeFilters={ @ComponentScan.Filter(Controller.class)},
excludeFilters={ @ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class) })
public class AppConfig {

}
