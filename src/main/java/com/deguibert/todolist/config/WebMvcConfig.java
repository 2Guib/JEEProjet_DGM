package com.deguibert.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("package/jquery/**").addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.3.1-1/");
		registry.addResourceHandler("package/popper/**").addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.14.1/umd/");
		registry.addResourceHandler("package/bootstrap/**").addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.1.1/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	
}
