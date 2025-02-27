package com.calendarapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CalendarappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarappApplication.class, args);
	}
}
