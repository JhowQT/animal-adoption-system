package com.cp5.aiservice.model;

import lombok.Data;

@Data
public class AdoptionRequest {
    private Long adoptionId;
    private Long userId;
    private Long animalId;
}