package com.wcc.distance.finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * DistanceFinder Service Springboot initializer class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */

@SpringBootApplication
@EnableWebSecurity
public class DistanceFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistanceFinderApplication.class, args);
    }

}
