package com.cp5.animalservice.service;

import com.cp5.animalservice.model.Animal;
import com.cp5.animalservice.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public Animal create(Animal animal) {

        if (animal.getName() == null || animal.getName().isBlank()) {
            throw new RuntimeException("Nome obrigatório");
        }

        if (animal.getSpecies() == null) {
            throw new RuntimeException("Espécie obrigatória");
        }

        animal.setAdoptionStatus("AVAILABLE");

        return repository.save(animal);
    }

    public List<Animal> findAll() {
        return repository.findAll();
    }

    public Animal findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    public List<Animal> findAvailable() {
        return repository.findByAdoptionStatus("AVAILABLE");
    }

    public Animal updateStatus(Long id, String status) {

        Animal animal = findById(id);
        animal.setAdoptionStatus(status);

        return repository.save(animal);
    }
}