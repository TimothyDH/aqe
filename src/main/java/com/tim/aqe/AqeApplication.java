package com.tim.aqe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
//@EnableWebMvc
public class AqeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AqeApplication.class, args);
	}
}
