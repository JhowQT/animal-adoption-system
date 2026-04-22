package com.cp5.adoptionservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AnimalClient {

    private final RestTemplate restTemplate;

    public AnimalClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getAnimal(Long animalId) {
        return restTemplate.getForObject(
                "http://localhost:8082/animals/" + animalId,
                Object.class
        );
    }
}