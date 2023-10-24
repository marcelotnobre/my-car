package com.marcelo.api.controllers;

import com.marcelo.api.domain.Car;
import com.marcelo.api.domain.User;
import com.marcelo.api.dto.CarDTO;
import com.marcelo.api.services.CarService;
import com.marcelo.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarControlller {

    private final CarService service;


    @Autowired
    public CarControlller(CarService carService, UserService service) {
        this.service = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(service.findCarsByUserId(user.getId()));
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car, Authentication authentication) {
        return ResponseEntity.ok(service.save(car, (User) authentication.getPrincipal()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(service.findCarByCarIdAndUserId(id, user.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        this.service.delete(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CarDTO> update(@Valid CarDTO carDTO, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(CarDTO.fromCar(this.service.save(carDTO.toCar(), user)));
    }
}
