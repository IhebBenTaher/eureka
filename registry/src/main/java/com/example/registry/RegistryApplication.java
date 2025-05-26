package com.example.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaServer
@RestController
public class RegistryApplication {
	@GetMapping
	public String hello(){
		return "hello from registry";
	}
	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}

}
