package com.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lab")
public class LaboratoryManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(LaboratoryManagementApplication.class, args);
	}

}
