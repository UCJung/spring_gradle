package com.spay.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.spay.web.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 정적 리소스를 처리 핸들러를 등록
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        
    }
    
    /**
     * 인터셉터 설정 
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    	localeChangeInterceptor.setParamName("lang");
    	registry.addInterceptor(localeChangeInterceptor);
    }
    
    /**
     * Locale Resolver 설정  
     * @return
     */
    @Bean
    public LocaleResolver localResolver(){
    	CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    	cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
    	return cookieLocaleResolver;
    }
    
    /**
     * View Resover 설정 : /WEB-INF/views/*.jsp를 뷰로 사용
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
    	
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    	viewResolver.setViewClass(JstlView.class);
    	viewResolver.setPrefix("/WEB-INF/views/");
    	viewResolver.setSuffix(".jsp");
    	
    	return viewResolver;
    }
    
    /**
     * View Controller 설정 
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	
    	// controller를 타지 않아도 되는 요청에 대하여 등록 
    	registry.addViewController("/simpleView");

    }
    
    /**
     * message source 등록  
     * @return
     */
    @Bean
    public MessageSource messageSource() {
    	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    	messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation");
    	
    	messageSource.setUseCodeAsDefaultMessage(true);
    	messageSource.setDefaultEncoding("UTF-8");
    	
    	messageSource.setCacheSeconds(0);
    	return messageSource;
    }
}
