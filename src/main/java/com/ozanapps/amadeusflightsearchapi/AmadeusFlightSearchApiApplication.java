package com.ozanapps.amadeusflightsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AmadeusFlightSearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmadeusFlightSearchApiApplication.class, args);
    }
}
