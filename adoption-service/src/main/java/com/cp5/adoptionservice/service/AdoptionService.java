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
            System.out.println("Chamando USER SERVICE...");
            Object user = userClient.getUser(userId);
            System.out.println("Usuário OK: " + user);

            // 🔗 validar animal
            System.out.println("Chamando ANIMAL SERVICE...");
            Object animal = animalClient.getAnimal(animalId);
            System.out.println("Animal OK: " + animal);

        } catch (Exception e) {
            System.out.println("❌ ERRO AO CHAMAR MICROSERVIÇOS");
            e.printStackTrace(); // 🔥 ESSENCIAL PRA DEBUG
            throw new RuntimeException("Erro ao validar usuário ou animal");
        }

        try {
            // 🔥 criação da adoção
            Adoption adoption = new Adoption();
            adoption.setUserId(userId);
            adoption.setAnimalId(animalId);
            adoption.setStatus(AdoptionStatus.REQUESTED);
            adoption.setRequestDate(LocalDateTime.now());

            Adoption saved = repository.save(adoption);

            System.out.println("✅ ADOÇÃO SALVA: " + saved.getId());

            // 🐇 envio para IA
            producer.sendAdoptionRequest(saved);
            System.out.println("📩 Evento enviado para fila");

            return saved;

        } catch (Exception e) {
            System.out.println("❌ ERRO AO SALVAR ADOÇÃO");
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