package com.marcelo.api.dto;

import com.marcelo.api.domain.Car;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarDTO {

    Long id;

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

    public Car toCar() {
        Car car = new Car();
        car.setId(this.getId());
        car.setYear(this.getYear());
        car.setLicensePlate(this.getLicensePlate());
        car.setModel(this.getModel());
        car.setColor(this.getColor());
        return car;
    }

    public static CarDTO fromCar(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setYear(car.getYear());
        dto.setLicensePlate(car.getLicensePlate());
        dto.setModel(car.getModel());
        dto.setColor(car.getColor());
        return dto;
    }

}
