package com.rasrov.kairos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KairosApplication {
    public static void main(String[] args) {
        SpringApplication.run(KairosApplication.class, args);
    }
}