package com.recetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RecetasSegurasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecetasSegurasApiApplication.class, args);
    }
}
