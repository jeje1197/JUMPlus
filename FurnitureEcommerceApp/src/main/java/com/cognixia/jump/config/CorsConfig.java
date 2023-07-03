package com.cognixia.jump.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsConfig implements WebMvcConfigurer {
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistration registry) {
//				registry.addMapping("/**")
//				.allowedMethods( "GET", "POST", "PUT", "DELETE", "PATCH" )
//				.allowedOrigins("*")
//				.allowedHeaders("*");
//			}
//		};
//	}
}
