package com.jamesobin.hour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HourApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HourApplication.class, args);
	}

}
