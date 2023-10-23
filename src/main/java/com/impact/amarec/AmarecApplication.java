package com.impact.amarec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.impact.amarec.entity")
public class AmarecApplication {
	public static void main(String[] args) {
		SpringApplication.run(AmarecApplication.class, args);
	}
}
