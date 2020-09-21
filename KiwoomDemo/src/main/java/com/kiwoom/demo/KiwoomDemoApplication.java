package com.kiwoom.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class KiwoomDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(KiwoomDemoApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KiwoomDemoApplication.class);
	}
}
