package com.cp5.adoptionservice.service;

import com.cp5.adoptionservice.client.AnimalClient;
import com.cp5.adoptionservice.client.UserClient;
import com.cp5.adoptionservice.messaging.AdoptionProducer;
import com.cp5.adoptionservice.model.*;
import com.cp5.adoptionservice.repository.AdoptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdoptionService {

    private final AdoptionRepository repository;
    private final UserClient userClient;
    private final AnimalClient animalClient;
    private final AdoptionProducer producer;

    public AdoptionService(AdoptionRepository repository,
                           UserClient userClient,
                           AnimalClient animalClient,
                           AdoptionProducer producer) {
        this.repository = repository;
        this.userClient = userClient;
        this.animalClient = animalClient;
        this.producer = producer;
    }

    public Adoption create(Long userId, Long animalId) {

        // 🔗 validar usuário
        userClient.getUser(userId);

        // 🔗 validar animal
        animalClient.getAnimal(animalId);

        Adoption adoption = Adoption.builder()
                .userId(userId)
                .animalId(animalId)
                .status(AdoptionStatus.REQUESTED)
                .requestDate(LocalDateTime.now())
                .build();

        Adoption saved = repository.save(adoption);

        // 🐇 envia para IA
        producer.sendAdoptionRequest(saved);

        return saved;
    }

    public List<Adoption> findAll() {
        return repository.findAll();
    }

    public Adoption findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adoção não encontrada"));
    }

    public List<Adoption> findByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}