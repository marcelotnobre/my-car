package com.marcelo.api.controllers;

import com.marcelo.api.domain.Car;
import com.marcelo.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarControlller {

    private final CarService carService;

    @Autowired
    public CarControlller(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
