package com.example.spring_application2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringApplication2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringApplication2Application.class, args);
	}

}
