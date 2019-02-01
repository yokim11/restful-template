package com.demo.appname.api.common.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        
        registry.addResourceHandler("/**")
        		.addResourceLocations("classpath:/static/");
    }
    
    @Bean
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
    	HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    	factory.setHttpClient(HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(50).build());
    	factory.setConnectTimeout(3000);
    	factory.setReadTimeout(3000);
    	return factory;
    }
}
