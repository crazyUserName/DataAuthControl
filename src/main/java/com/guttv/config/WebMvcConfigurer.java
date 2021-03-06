package com.guttv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.guttv.interceptor.DataAuthInterceptor;


@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	@Autowired
	private DataAuthInterceptor dataAuthInterceptor;
	
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataAuthInterceptor).addPathPatterns("/**");
	 }

}
