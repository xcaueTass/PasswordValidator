package com.example.pass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example" })
public class PassApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassApplication.class, args);
	}

}
