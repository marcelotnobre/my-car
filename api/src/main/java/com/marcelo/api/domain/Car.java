package com.marcelo.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    Long id;
    @Column(name = "manufacture_year")
    Integer year;
    @NotNull
    @NotBlank
    String licensePlate;
    @NotNull
    @NotBlank
    String model;
    @NotNull
    @NotBlank
    String color;

    public Car() {
    }
}
