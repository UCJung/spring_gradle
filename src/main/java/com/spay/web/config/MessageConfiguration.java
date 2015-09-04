package com.spay.web.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.spay.core.constants.SystemConstants;

@Configuration
public class MessageConfiguration {
	private static final String SYSTEM_PROPERTIES = "classpath*:messages/**/*.xml";
	
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() throws IOException {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources(SYSTEM_PROPERTIES);

        messageSource.setBasenames(getResourcePath(resources));
        messageSource.setDefaultEncoding(SystemConstants.DEFAULT_ENCODE);
        messageSource.setCacheSeconds(SystemConstants.PROPERTIES_REFRESH_SECONDS);

        return messageSource;
    }

    public String[] getResourcePath(Resource[] resources) {
        List<String> list = new ArrayList<String>();

        for (Resource resource : resources) {
            try {
                String path = resource.getURI().toString();
                String[] ary = path.split(SystemConstants.PROPERTIES_SEPERATOR);
                String newPath = "";
                int length = ary.length;

                for (int i = 0; i < length - 2; i++) {
                    if (i == 0) {
                        newPath = newPath + ary[i];
                    } else {
                        newPath = newPath + SystemConstants.PROPERTIES_SEPERATOR + ary[i];
                    }
                }

                list.add(newPath);
            } catch (IOException e) {

            }
        }

        String[] arys = new String[list.size()];

        return list.toArray(arys);
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() throws IOException {
        return new MessageSourceAccessor(messageSource());
    }	
}
