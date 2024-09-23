package com.apiaerolinea.ciudad_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CiudadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CiudadServiceApplication.class, args);
	}

}
