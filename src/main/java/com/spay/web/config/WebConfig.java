package com.spay.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan( 
		basePackages={ "com.spay.module", "com.spay.web" } )
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 정적 리소스를 처리 핸들러를 등록
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    	configurer.enable();
    }
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
        	.favorPathExtension(true)
        	.useJaf(false)
        	.ignoreAcceptHeader(false)
        	.mediaType("html", MediaType.TEXT_HTML)
        	.mediaType("json", MediaType.APPLICATION_JSON)
        	.mediaType("jsonp", MediaType.APPLICATION_JSON)
        	.mediaType("xml", MediaType.APPLICATION_XML)
        	.defaultContentType(MediaType.APPLICATION_JSON);
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
