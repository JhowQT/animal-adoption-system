package com.cp5.animalservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getUserById(Long userId) {

        String url = "http://localhost:8081/users/" + userId;

        return restTemplate.getForObject(url, Object.class);
    }
}