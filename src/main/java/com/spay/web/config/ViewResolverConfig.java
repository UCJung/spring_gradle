package com.spay.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * set ViewResolver Config
 * @author NHNent
 *
 */

@Configuration
public class ViewResolverConfig {
	
    /**
     * Jstl View Resolver Config : /WEB-INF/views/*.jsp
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
    	
    	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    	viewResolver.setOrder(2);
    	viewResolver.setViewClass(JstlView.class);
    	viewResolver.setPrefix("/WEB-INF/views/");
    	viewResolver.setSuffix(".jsp");
    	
    	return viewResolver;
    }
	
	/**
	 * FreeMarker View Resolver Config : /WEB-INF/views/*.ftl
	 * @return
	 */
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		
		FreeMarkerConfigurer fmc = new FreeMarkerConfigurer();
		fmc.setTemplateLoaderPath("/WEB-INF/views/");
		fmc.setDefaultEncoding("utf-8");

		return fmc;
	}
	
	@Bean(name=DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {
		FreeMarkerViewResolver fmvr = new FreeMarkerViewResolver();
		fmvr.setOrder(0);
		fmvr.setContentType("text/html; charset=utf-8");
		fmvr.setCache(true);
		fmvr.setPrefix("");
		fmvr.setSuffix(".ftl");
		fmvr.setRequestContextAttribute("rc");
		
		return fmvr;
	}
}
