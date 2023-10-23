package com.marcelo.api.services;

import com.marcelo.api.domain.Car;
import com.marcelo.api.domain.User;
import com.marcelo.api.repository.CarResipository;
import com.marcelo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarResipository carResipository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public CarService(CarResipository carResipository) {
        this.carResipository = carResipository;
    }

    public Car save(Car car, User user) {
        List<Car> cars = carResipository.findCarsByUserId(user.getId());
        var carSaved = this.carResipository.save(car);
        cars.add(carSaved);
        user.setCars(cars);
        userRepository.save(user);
        return carSaved;
    }

    public Car findCarByCarIdAndUserId(Long id, Long idUser) {
        return this.carResipository.findCarByCarIdAndUserId(id, idUser);
    }

    public void delete(Long id, Long idUser) {
        this.carResipository.delete(this.findCarByCarIdAndUserId(id, idUser));
    }
}
