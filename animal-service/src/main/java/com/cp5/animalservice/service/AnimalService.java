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

    // Criar animal
    public Animal create(Animal animal) {

        if (animal.getName() == null || animal.getName().isBlank()) {
            throw new RuntimeException("Nome obrigatório");
        }

        if (animal.getSpecies() == null) {
            throw new RuntimeException("Espécie obrigatória");
        }

        // padrão: disponível
        animal.setAdoptionStatus("AVAILABLE");

        return repository.save(animal);
    }

    // Listar todos
    public List<Animal> findAll() {
        return repository.findAll();
    }

    // Buscar por id
    public Animal findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    // Listar disponíveis
    public List<Animal> findAvailable() {
        return repository.findByAdoptionStatus("AVAILABLE");
    }

    // Atualizar status (importante pra adoção)
    public Animal updateStatus(Long id, String status) {

        Animal animal = findById(id);

        animal.setAdoptionStatus(status);

        return repository.save(animal);
    }
}