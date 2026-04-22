package com.cp5.aiservice.messaging;

import com.cp5.aiservice.model.AdoptionResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdoptionProducer {

    private final RabbitTemplate rabbitTemplate;

    public AdoptionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendResponse(AdoptionResponse response) {
        rabbitTemplate.convertAndSend(
                "adoption.exchange",
                "adoption.response",
                response
        );
    }
}