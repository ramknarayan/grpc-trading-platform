package org.example.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AggregatorServiceApplicaton {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        SpringApplication.run(AggregatorServiceApplicaton.class,args);
    }
}