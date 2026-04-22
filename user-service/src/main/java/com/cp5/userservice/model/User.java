package com.cp5.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "nm_user", nullable = false, length = 100)
    private String name;

    @Column(name = "ds_email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "nr_age", nullable = false)
    private Integer age;

    @Column(name = "usr_password", nullable = false, length = 150)
    private String password;

    @Column(name = "ds_city", length = 100)
    private String city;

    @Column(name = "fl_has_yard")
    private Boolean hasYard;

    @Column(name = "ds_home_type", length = 50)
    private String homeType;

    @Column(name = "ds_experience_level", length = 50)
    private String experienceLevel;

    @Column(name = "ds_preferred_animal_size", length = 20)
    private String preferredAnimalSize;

    @Column(name = "fl_active", nullable = false)
    private Boolean active;
}
//teste