package com.apiaerolineas.avion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AvionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvionServiceApplication.class, args);
	}

}
