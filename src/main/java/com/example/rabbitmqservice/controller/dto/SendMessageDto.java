package com.example.rabbitmqservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SendMessageDto {
    private String message;
    private String taxiId;
}
