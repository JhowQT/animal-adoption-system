package com.cp5.adoptionservice.model;

import lombok.Data;

@Data
public class AdoptionResponse {

    private Long adoptionId;
    private Double score;
    private String decision;
    private String reason;
}