package com.cp5.adoptionservice.controller;

import com.cp5.adoptionservice.model.Adoption;
import com.cp5.adoptionservice.service.AdoptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {

    private final AdoptionService service;

    public AdoptionController(AdoptionService service) {
        this.service = service;
    }

    @PostMapping
    public Adoption create(@RequestParam Long userId,
                           @RequestParam Long animalId) {
        return service.create(userId, animalId);
    }

    @GetMapping
    public List<Adoption> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Adoption getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Adoption> getByUser(@PathVariable Long userId) {
        return service.findByUser(userId);
    }
}