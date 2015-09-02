package com.spay.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * set ViewResolver Config
 * @author NHNent
 *
 */

@Configuration
public class ViewResolverConfig {

	
	/**
	 * 
	 * @param manager
	 * @return
	 */
	@Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setOrder(1);
        contentNegotiatingViewResolver.setContentNegotiationManager(manager);
        
        List<View> views = new ArrayList<View>();
        views.add(new CommonMappingJackson2JsonView());
        contentNegotiatingViewResolver.setDefaultViews(views);

        return contentNegotiatingViewResolver;
    }	
	
	/**
	 * Tiles View Resolver Config : /WEB-INF/views/tiles/*.jsp
	 */

	@Bean
	public TilesConfigurer tilesConfigurer() {
		  TilesConfigurer tilesConfigurer = new TilesConfigurer();
		  tilesConfigurer.setCheckRefresh(true);
		  tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/**/tiles.xml"});

		  return tilesConfigurer;
	}	
	
	@Bean
	public UrlBasedViewResolver viewResolverForURL() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		viewResolver.setOrder(2);
		return viewResolver;
	}	
	
    /**
     * Jstl View Resolver Config : /WEB-INF/views/*.jsp
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
    	
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    	resolver.setOrder(3);
    	resolver.setViewClass(JstlView.class);
    	resolver.setPrefix("/WEB-INF/views/");
    	resolver.setSuffix(".jsp");
    	
    	return resolver;
    }
	
	@Bean(name=DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxInMemorySize(100000000);
		resolver.setMaxUploadSize(200000000);		
		return resolver;
	}
}
