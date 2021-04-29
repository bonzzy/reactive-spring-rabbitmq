package com.example.rabbitmqservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.Sender;

@SpringBootApplication
public class RabbitmqServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqServiceApplication.class, args);
    }
}
