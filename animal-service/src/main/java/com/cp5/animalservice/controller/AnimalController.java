package com.cp5.animalservice.controller;

import com.cp5.animalservice.model.Animal;
import com.cp5.animalservice.service.AnimalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    // Criar
    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return service.create(animal);
    }

    // Listar todos
    @GetMapping
    public List<Animal> getAll() {
        return service.findAll();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public Animal getById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Listar disponíveis
    @GetMapping("/available")
    public List<Animal> getAvailable() {
        return service.findAvailable();
    }

    // Atualizar status
    @PutMapping("/{id}/status")
    public Animal updateStatus(@PathVariable Long id,
                               @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}