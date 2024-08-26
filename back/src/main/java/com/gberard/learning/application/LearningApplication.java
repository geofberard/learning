package com.gberard.learning.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages={
		"com.gberard.learning.application",
		"com.gberard.learning.domain",
		"com.gberard.learning.infrastructure"
})
@EnableJpaRepositories(basePackages = "com.gberard.learning.infrastructure")
@EntityScan(basePackages = "com.gberard.learning.infrastructure")
public class LearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(
								"http://localhost:3000",
								"https://geofberard.github.io/"
						);
			}
		};
	}

}
