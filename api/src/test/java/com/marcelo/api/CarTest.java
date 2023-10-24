package com.marcelo.api;

import com.marcelo.api.domain.Car;
import com.marcelo.api.domain.User;
import com.marcelo.api.exceptions.AppException;
import com.marcelo.api.repository.CarResipository;
import com.marcelo.api.repository.UserRepository;
import com.marcelo.api.services.CarService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarResipository carResipository;

    @Mock
    private UserRepository userRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void saveCarLicensePlateAlreadyExistsFailure() {
        var car = new Car();
        var licensePlate = "WDG123";
        car.setLicensePlate(licensePlate);
        var user = new User();
        Mockito.when(carResipository.findByLicensePlate(licensePlate)).thenReturn(Optional.of(car));
        expectedException.expect(AppException.class);
        expectedException.expectMessage("License plate already exists");
        carService.save(car, user);
    }

    @Test
    public void saveSuccess() {
        var car = new Car();
        var licensePlate = "WDG123";
        car.setLicensePlate(licensePlate);
        var user = new User();
        Mockito.when(carResipository.findByLicensePlate(licensePlate)).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(carResipository.save(car)).thenReturn(car);
        var licensePlateSaved = carService.save(car, user).getLicensePlate();
        Assert.assertEquals(licensePlate, licensePlateSaved);
    }
}
