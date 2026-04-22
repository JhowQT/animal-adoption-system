package com.cp5.aiservice.service;

import com.cp5.aiservice.integration.OpenAiClient;
import com.cp5.aiservice.model.AdoptionRequest;
import com.cp5.aiservice.model.AdoptionResponse;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final OpenAiClient client;

    public AiService(OpenAiClient client) {
        this.client = client;
    }

    public AdoptionResponse analyzeAdoption(AdoptionRequest request) {

        String prompt = """
        Analise a adoção de um animal.

        User ID: %d
        Animal ID: %d

        Retorne:
        - score (0 a 1)
        - decision (APPROVED ou REJECTED)
        - reason
        """.formatted(request.getUserId(), request.getAnimalId());

        String response = client.analyze(prompt);

        double score = Math.random();

        String decision;
        if (score > 0.5) {
            decision = "APPROVED";
        } else {
            decision = "REJECTED";
        }

        return AdoptionResponse.builder()
                .adoptionId(request.getAdoptionId())
                .score(score)
                .decision(decision)
                .reason(response)
                .build();
    }
}