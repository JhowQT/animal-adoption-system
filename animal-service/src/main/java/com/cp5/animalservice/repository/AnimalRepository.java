package com.cp5.animalservice.repository;

import com.cp5.animalservice.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByAdoptionStatus(String status);
}