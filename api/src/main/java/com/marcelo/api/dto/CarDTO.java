package com.marcelo.api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarDTO {
    @NotNull
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
}
