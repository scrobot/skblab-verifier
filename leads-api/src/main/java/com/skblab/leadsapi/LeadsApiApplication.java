package com.skblab.leadsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class LeadsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeadsApiApplication.class, args);
    }

}