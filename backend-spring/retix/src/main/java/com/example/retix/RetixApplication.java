package com.example.retix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // This annotation is a combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class RetixApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetixApplication.class, args);  // Start the Spring Boot application
    }
}
