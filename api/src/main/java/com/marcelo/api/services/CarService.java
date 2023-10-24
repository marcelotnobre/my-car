package com.marcelo.api.services;

import com.marcelo.api.domain.Car;
import com.marcelo.api.domain.User;
import com.marcelo.api.exceptions.AppException;
import com.marcelo.api.repository.CarResipository;
import com.marcelo.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarResipository repository;
    private final UserRepository userRepository;

    @Autowired
    public CarService(CarResipository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Car save(Car car, User user) {

        this.validLicencePlate(car);
        List<Car> cars = this.repository.findCarsByUserId(user.getId());
        var carSaved = this.repository.save(car);
        cars.add(carSaved);
        user.setCars(cars);
        userRepository.save(user);
        return carSaved;
    }

    public Car findCarByCarIdAndUserId(Long id, Long idUser) {
        return this.userRepository.findCarByCarIdAndUserId(id, idUser)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public void delete(Long id, Long idUser) {
        this.repository.delete(this.findCarByCarIdAndUserId(id, idUser));
    }

    public List<Car> findCarsByUserId(Long userId) {
        return this.repository.findCarsByUserId(userId);
    }

    public Car update(User user, Car car) {
        this.findCarByCarIdAndUserId(car.getId(), user.getId());
        return save(car, user);
    }

    private void validLicencePlate(Car car) {
        var carDatabase = this.repository.findByLicensePlate(car.getLicensePlate());
        if (carDatabase.isEmpty()) {
            return;
        }
        if (car.getId() != null && car.getId().compareTo(carDatabase.get().getId()) == 0) {
            return;
        }
        if (carDatabase != null) {
            throw new AppException("License plate already exists", 3);
        }
    }
}
