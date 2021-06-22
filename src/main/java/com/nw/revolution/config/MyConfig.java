package com.nw.revolution.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//Path uploadDir = Paths.get("./static");
		//String uploadPath = uploadDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

}
