package com.marcelo.api.controllers;

import com.marcelo.api.domain.Car;
import com.marcelo.api.domain.User;
import com.marcelo.api.services.CarService;
import com.marcelo.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarControlller {

    private final CarService carService;

    private final UserService userService;

    @Autowired
    public CarControlller(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.findCarsByUserId(user.getId()));
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car, Authentication authentication) {
        return ResponseEntity.ok(carService.save(car, (User) authentication.getPrincipal()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(carService.findCarByCarIdAndUserId(id, user.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        this.carService.delete(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
