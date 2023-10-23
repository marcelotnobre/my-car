package com.marcelo.api.repository;

import com.marcelo.api.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarResipository extends JpaRepository<Car, Long> {

    @Query("SELECT u.cars FROM User u WHERE u.id = :userId")
    List<Car> findCarsByUserId(@Param("userId") Long userId);

    @Query("SELECT c FROM Car c WHERE c.id = :carId AND c.user.id = :userId")
    Car findCarByCarIdAndUserId(@Param("carId") Long carId, @Param("userId") Long userId);
}
