package com.cp5.adoptionservice.model;

import lombok.Data;

@Data
public class AdoptionRequest {

    private Long adoptionId;
    private Long userId;
    private Long animalId;
}