package com.tru.surveyrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SurveyRestApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SurveyRestApplication.class, args);
	}
	
	@Profile("prod")
	@Bean
	public String dummy() {
		return "some-text";
	}

}

