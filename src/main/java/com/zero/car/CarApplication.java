package com.zero.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Sore
 */
@SpringBootApplication
public class CarApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CarApplication.class, args);
    }

}
