package com.order.rush_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.order.rush_order")
public class RushOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RushOrderApplication.class, args);
    }

}
