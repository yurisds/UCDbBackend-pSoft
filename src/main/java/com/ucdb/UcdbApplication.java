package com.ucdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ucdb.filter.TokenFilter;

@SpringBootApplication
public class UcdbApplication {

	@Bean
	public FilterRegistrationBean filterJwt() {
		FilterRegistrationBean filterRb = new FilterRegistrationBean();
		filterRb.setFilter(new TokenFilter());
		filterRb.addUrlPatterns("/private");
		return filterRb;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(UcdbApplication.class, args);
	}

}
