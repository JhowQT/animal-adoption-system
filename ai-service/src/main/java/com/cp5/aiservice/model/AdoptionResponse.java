package com.cp5.aiservice.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionResponse {

    private Long adoptionId;
    private Double score;
    private String decision;
    private String reason;
}