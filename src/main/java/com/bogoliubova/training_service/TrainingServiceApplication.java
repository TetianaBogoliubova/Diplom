package com.bogoliubova.training_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(value = "com.bogoliubova.training_service")
@ComponentScan(value =  "com.bogoliubova.training_service.aspect")
public class TrainingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingServiceApplication.class, args);
    }
}


