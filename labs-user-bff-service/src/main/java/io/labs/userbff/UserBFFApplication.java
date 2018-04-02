package io.labs.userbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserBFFApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserBFFApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public AsyncRestTemplate asyncRestTemplate() {
		return new AsyncRestTemplate();
	}
}
