package com.example.marcelo.mycars.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    Long id;
    String firstName;
    String lastName;
    String email;
    LocalDate birthday;
    String login;
    String password;
    String phone;

    @OneToMany
    @JoinColumn(name="car_id")
    List<Car> cars;


    public User() {
    }
}
