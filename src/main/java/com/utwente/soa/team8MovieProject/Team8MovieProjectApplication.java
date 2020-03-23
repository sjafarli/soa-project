package com.utwente.soa.team8MovieProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Team8MovieProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team8MovieProjectApplication.class, args);
	}

}
