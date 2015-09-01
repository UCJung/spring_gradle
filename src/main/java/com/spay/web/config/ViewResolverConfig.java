package com.spay.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

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
    	viewResolver.setOrder(3);
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
		fmc.setTemplateLoaderPath("/WEB-INF/views/freemarker");
		fmc.setDefaultEncoding("utf-8");

		return fmc;
	}
	
	@Bean(name=DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	@Bean
	public FreeMarkerViewResolver viewResolverForFreeMarker() {
		FreeMarkerViewResolver fmvr = new FreeMarkerViewResolver();
		fmvr.setOrder(1);
		fmvr.setContentType("text/html; charset=utf-8");
		fmvr.setCache(true);
		fmvr.setPrefix("/WEB-INF/views/freemarker/");
		fmvr.setSuffix(".jsp");
		fmvr.setRequestContextAttribute("rc");
		
		return fmvr;
	}    
    
	/**
	 * Tiles View Resolver Config : /WEB-INF/views/tiles/*.jsp
	 */
	
	@Bean
	public UrlBasedViewResolver viewResolverForURL() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}	
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		  TilesConfigurer tilesConfigurer = new TilesConfigurer();
		  tilesConfigurer.setCheckRefresh(true);
		  tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/**/tiles.xml"});

		  return tilesConfigurer;
	}
	
	@Bean
	public TilesViewResolver getTilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(0);
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}	
}
