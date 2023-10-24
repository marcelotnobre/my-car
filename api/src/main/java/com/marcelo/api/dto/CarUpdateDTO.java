package com.marcelo.api.dto;

import com.marcelo.api.domain.Car;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarUpdateDTO {

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

    public static CarUpdateDTO fromCar(Car car) {
        CarUpdateDTO dto = new CarUpdateDTO();
        dto.setYear(car.getYear());
        dto.setLicensePlate(car.getLicensePlate());
        dto.setModel(car.getModel());
        dto.setColor(car.getColor());
        return dto;
    }
}
