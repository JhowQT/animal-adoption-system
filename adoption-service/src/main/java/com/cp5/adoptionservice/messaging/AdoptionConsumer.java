package com.cp5.adoptionservice.messaging;

import com.cp5.adoptionservice.model.Adoption;
import com.cp5.adoptionservice.model.AdoptionResponse;
import com.cp5.adoptionservice.model.AdoptionStatus;
import com.cp5.adoptionservice.repository.AdoptionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdoptionConsumer {

    private final AdoptionRepository repository;

    public AdoptionConsumer(AdoptionRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "adoption.response.queue")
    public void receive(AdoptionResponse response) {

        Adoption adoption = repository.findById(response.getAdoptionId())
                .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));

        // Atualiza dados vindos da IA
        adoption.setAiScore(response.getScore());
        adoption.setNotes(response.getReason());
        adoption.setApprovalDate(LocalDateTime.now());

        // Atualiza status
        if ("APPROVED".equals(response.getDecision())) {
            adoption.setStatus(AdoptionStatus.APPROVED);
        } else {
            adoption.setStatus(AdoptionStatus.REJECTED);
        }

        repository.save(adoption);
    }
}