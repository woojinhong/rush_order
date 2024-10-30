package com.order.rush_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RushOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RushOrderApplication.class, args);
    }
}
