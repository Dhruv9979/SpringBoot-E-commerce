package com.Springboot.E_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);
	}

}
