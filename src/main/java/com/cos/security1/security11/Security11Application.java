package com.cos.security1.security11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Security11Application {

	public static void main(String[] args) {
		SpringApplication.run(Security11Application.class, args);
	}

}
