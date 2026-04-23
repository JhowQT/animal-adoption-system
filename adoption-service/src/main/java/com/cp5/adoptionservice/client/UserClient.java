package com.cp5.adoptionservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getUser(Long id) {
        return restTemplate.getForObject(
                "http://USER-SERVICE/users/" + id,
                Object.class
        );
    }
}