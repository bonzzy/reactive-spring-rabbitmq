package com.example.rabbitmqservice.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.*;

@Configuration
public class DefaultRabbitMqClient {
    static final String QUEUE = "reactor.spring.boot.test";

    @Bean()
    Mono<Connection> connectionMonoBean(RabbitProperties rabbitProperties) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        return Mono.fromCallable(() -> connectionFactory.newConnection("reactor-rabbit")).cache();
    }

    @Bean
    Sender sender(Mono<Connection> currConnectionMono) {
        return RabbitFlux.createSender(new SenderOptions().connectionMono(currConnectionMono));
    }

    @Bean
    Receiver receiver(Mono<Connection> currConnectionMono) {
        return RabbitFlux.createReceiver(new ReceiverOptions().connectionMono(currConnectionMono));
    }

    @Bean
    Flux<Delivery> deliveryFlux(Receiver receiver) {
        return receiver.consumeNoAck(QUEUE);
    }
}
