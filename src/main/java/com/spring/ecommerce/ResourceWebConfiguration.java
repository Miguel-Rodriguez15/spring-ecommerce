package com.spring.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//indicamos que sera una configuracion
@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer{ //implementamos la inbterface

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//indico que me traiga todo lo que esta en ese directorio y agrego la locacion 
		registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
		
	}
	
}
