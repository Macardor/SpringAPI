package com.codecool.SpringAPI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApiApplication {
    public static final Logger logger = LogManager.getLogger(SpringApiApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringApiApplication.class, args);
    }
}
