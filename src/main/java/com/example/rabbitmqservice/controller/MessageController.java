package com.example.rabbitmqservice.controller;

import com.example.rabbitmqservice.controller.dto.SendMessageDto;
import com.example.rabbitmqservice.service.SenderClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MessageController {
    final SenderClient senderClient;

    MessageController(SenderClient senderClient) {
        this.senderClient = senderClient;
    }

    @PostMapping("/send")
    public Mono<ResponseEntity> sendMessage(@RequestBody SendMessageDto sendMessageDto) {
        senderClient.send(sendMessageDto.getMessage(), "amq.direct", "taxi." + sendMessageDto.getTaxiId());
        return Mono.just(ResponseEntity.ok(sendMessageDto));
    }
}
