package com.cp5.adoptionservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_adoption")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adoption")
    private Long id;

    @Column(name = "id_user", nullable = false)
    private Long userId;

    @Column(name = "id_animal", nullable = false)
    private Long animalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_status")
    private AdoptionStatus status;

    @Column(name = "dt_request")
    private LocalDateTime requestDate;

    @Column(name = "dt_approval")
    private LocalDateTime approvalDate;

    @Column(name = "nr_ai_score")
    private Double aiScore;

    @Column(name = "ds_notes")
    private String notes;
}