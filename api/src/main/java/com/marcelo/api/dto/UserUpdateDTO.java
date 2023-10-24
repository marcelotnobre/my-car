package com.marcelo.api.dto;

import com.marcelo.api.domain.Car;
import com.marcelo.api.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class UserUpdateDTO {
    @NotNull
    Long id;
    @NotNull
    @NotBlank
    String firstName;
    @NotNull
    @NotBlank
    String lastName;
    @NotNull
    @NotBlank
    String email;
    @NotNull
    LocalDate birthday;
    @NotNull
    @NotBlank
    String login;
    @NotNull
    @NotBlank
    String password;
    @NotNull
    @NotBlank
    String phone;
    List<Car> cars;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setBirthday(this.birthday);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setCars(this.cars);
        return user;
    }
}
