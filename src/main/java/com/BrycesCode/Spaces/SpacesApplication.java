package com.BrycesCode.Spaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpacesApplication.class, args);
	}

}
