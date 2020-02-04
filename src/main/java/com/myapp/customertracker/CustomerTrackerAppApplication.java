package com.myapp.customertracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy
public class CustomerTrackerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerTrackerAppApplication.class, args);
	}
}
