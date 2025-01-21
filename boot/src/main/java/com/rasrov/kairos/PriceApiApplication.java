package com.rasrov.kairos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PriceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PriceApiApplication.class, args);
    }
}