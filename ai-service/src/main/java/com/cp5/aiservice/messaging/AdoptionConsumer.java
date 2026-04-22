package com.cp5.aiservice.messaging;

import com.cp5.aiservice.model.AdoptionRequest;
import com.cp5.aiservice.model.AdoptionResponse;
import com.cp5.aiservice.service.AiService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AdoptionConsumer {

    private final AiService aiService;
    private final AdoptionProducer producer;

    public AdoptionConsumer(AiService aiService, AdoptionProducer producer) {
        this.aiService = aiService;
        this.producer = producer;
    }

    @RabbitListener(queues = "adoption.request.queue")
    public void receive(AdoptionRequest request) {

        AdoptionResponse response = aiService.analyzeAdoption(request);

        producer.sendResponse(response);
    }
}