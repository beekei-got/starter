package com.starter.place;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PlaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaceApplication.class, args);
    }

}
