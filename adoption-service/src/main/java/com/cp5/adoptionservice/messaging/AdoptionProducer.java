package com.cp5.adoptionservice.messaging;

import com.cp5.adoptionservice.model.Adoption;
import com.cp5.adoptionservice.model.AdoptionRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdoptionProducer {

    private final RabbitTemplate rabbitTemplate;

    public AdoptionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAdoptionRequest(Adoption adoption) {

        AdoptionRequest request = new AdoptionRequest();
        request.setAdoptionId(adoption.getId());
        request.setUserId(adoption.getUserId());
        request.setAnimalId(adoption.getAnimalId());

        rabbitTemplate.convertAndSend(
                "adoption.exchange",
                "adoption.request",
                request
        );
    }
}