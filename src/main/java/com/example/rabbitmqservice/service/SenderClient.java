package com.example.rabbitmqservice.service;

import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

@Service
public class SenderClient {
    final Sender sender;

    public SenderClient(Sender sender) {
        this.sender = sender;
    }

    public Disposable send(String message, String exchange, String routingKey) {
        return sender.send(Mono.just(new OutboundMessage(
                exchange,
                routingKey, message.getBytes()
        ))).subscribe();
    }
}
