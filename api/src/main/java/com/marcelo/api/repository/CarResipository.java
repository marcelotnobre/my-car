package com.marcelo.api.repository;

import com.marcelo.api.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarResipository extends JpaRepository<Car, Long> {
}
