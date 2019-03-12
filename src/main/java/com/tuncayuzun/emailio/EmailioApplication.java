package com.tuncayuzun.emailio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class EmailioApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailioApplication.class, args);
	}

}
