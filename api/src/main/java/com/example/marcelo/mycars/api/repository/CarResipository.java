package com.example.marcelo.mycars.api.repository;

import com.example.marcelo.mycars.api.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarResipository extends JpaRepository<Car, Long> {
}
