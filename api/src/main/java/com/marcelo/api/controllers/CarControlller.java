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
    public ResponseEntity<List<CarDTO>> findAll(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity
                .ok(service
                        .findCarsByUserId(user.getId())
                        .stream()
                        .map(CarDTO::fromCar)
                        .toList());
    }

    @PostMapping
    public ResponseEntity<CarDTO> save(@RequestBody Car car, Authentication authentication) {
        return ResponseEntity.ok(CarDTO.fromCar(service.save(car, (User) authentication.getPrincipal())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Long id, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(CarDTO.fromCar(service.findCarByCarIdAndUserId(id, user.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        this.service.delete(id, user.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable Long id, @Valid @RequestBody CarDTO carDTO, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        carDTO.setId(id);
        return ResponseEntity.ok(CarDTO.fromCar(this.service.update(user, carDTO.toCar())));
    }
}
