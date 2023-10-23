package com.marcelo.api.services;

import com.marcelo.api.domain.Car;
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

    public Car save(Car car) {
        //TODO ADICIONAR NO USUARIO LOGADO
        return this.carResipository.save(car);
    }

    public List<Car> findAll() {
        return this.carResipository.findAll();
    }

    public Car findById(Long id) {
        return this.findById(id);
    }

    public void delete(Long id) {
        this.carResipository.delete(this.findById(id));
    }
}
