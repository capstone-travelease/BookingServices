package com.BookingServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookingServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookingServicesApplication.class, args);
	}
}
