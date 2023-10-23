package com.marcelo.api.dto;

import com.marcelo.api.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    Long id;
    String firstName;
    String lastName;
    @Email
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
    //List<Car> cars;

    public User toUser() {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setBirthday(this.birthday);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        //user.setCars(this.cars);
        return user;
    }
}