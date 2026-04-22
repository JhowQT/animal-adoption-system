package com.cp5.adoptionservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getUser(Long userId) {
        return restTemplate.getForObject(
                "http://localhost:8081/users/" + userId,
                Object.class
        );
    }
}