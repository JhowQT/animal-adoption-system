package com.cp5.animalservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_animal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal")
    private Long id;

    @Column(name = "nm_animal", nullable = false)
    private String name;

    @Column(name = "ds_species", nullable = false)
    private String species;

    @Column(name = "ds_breed")
    private String breed;

    @Column(name = "nr_age")
    private Integer age;

    @Column(name = "ds_size")
    private String size;

    @Column(name = "ds_temperament")
    private String temperament;

    @Column(name = "ds_energy_level")
    private String energyLevel;

    @Column(name = "fl_requires_yard")
    private Boolean requiresYard;

    @Column(name = "fl_good_with_kids")
    private Boolean goodWithKids;

    @Column(name = "ds_adoption_status")
    private String adoptionStatus;

    @Column(name = "ds_description", length = 500)
    private String description;
}