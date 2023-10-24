package com.marcelo.api.repository;

import com.marcelo.api.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarResipository extends JpaRepository<Car, Long> {

    @Query("SELECT u.cars FROM User u WHERE u.id = :userId")
    List<Car> findCarsByUserId(@Param("userId") Long userId);

    Optional<Car> findByLicensePlate(String licensePlate);
}
