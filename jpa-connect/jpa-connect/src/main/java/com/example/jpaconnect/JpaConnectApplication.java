package com.example.jpaconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class JpaConnectApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaConnectApplication.class, args);
	}

}
