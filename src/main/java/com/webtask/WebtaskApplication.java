package com.webtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.webtask")
public class WebtaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebtaskApplication.class, args);
	}
}
