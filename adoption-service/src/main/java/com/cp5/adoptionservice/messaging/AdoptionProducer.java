package com.cp5.adoptionservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdoptionProducer {

    private final RabbitTemplate rabbitTemplate;

    public AdoptionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAdoptionRequest(Object adoption) {
        rabbitTemplate.convertAndSend("adoption.exchange", "adoption.request", adoption);
    }
}