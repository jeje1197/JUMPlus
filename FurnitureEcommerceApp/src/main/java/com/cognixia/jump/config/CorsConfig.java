package com.cognixia.jump.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
					.allowedMethods( "GET", "POST", "PUT", "DELETE", "PATCH" )
					.allowedOrigins("*")
					.allowedHeaders("*");
			}
			
		};
	}
}
