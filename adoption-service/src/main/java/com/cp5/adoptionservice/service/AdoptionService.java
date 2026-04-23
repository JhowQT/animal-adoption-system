package com.cp5.adoptionservice.service;

import com.cp5.adoptionservice.client.AnimalClient;
import com.cp5.adoptionservice.client.UserClient;
import com.cp5.adoptionservice.messaging.AdoptionProducer;
import com.cp5.adoptionservice.model.Adoption;
import com.cp5.adoptionservice.model.AdoptionStatus;
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

        try {
            System.out.println("===== INICIANDO ADOÇÃO =====");

            // 🔗 validar usuário
            userClient.getUser(userId);

            // 🔗 validar animal
            animalClient.getAnimal(animalId);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao validar usuário ou animal");
        }

        try {
            Adoption adoption = new Adoption();
            adoption.setUserId(userId);
            adoption.setAnimalId(animalId);
            adoption.setStatus(AdoptionStatus.REQUESTED);
            adoption.setRequestDate(LocalDateTime.now());

            Adoption saved = repository.save(adoption);

            System.out.println("✅ ADOÇÃO SALVA: " + saved.getId());

            // 🚫 DESATIVADO TEMPORARIAMENTE
            // producer.sendAdoptionRequest(saved);

            return saved;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar adoção");
        }
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