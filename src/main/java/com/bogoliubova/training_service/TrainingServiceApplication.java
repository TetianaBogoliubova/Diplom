package com.bogoliubova.training_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingServiceApplication.class, args);
    }


    //<include file="db/changelog/changes/v0.0.1-SNAPSHOT/RTN-283-create-tables-changelog.xml"/>
}
