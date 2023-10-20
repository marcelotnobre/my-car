package com.example.marcelo.mycars.api.domain;

import jakarta.persistence.*;
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
    String licensePlate;
    String model;
    String color;

    public Car() {
    }
}
