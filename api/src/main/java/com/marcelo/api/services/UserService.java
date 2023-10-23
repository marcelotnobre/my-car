package com.marcelo.api.services;

import com.marcelo.api.domain.User;
import com.marcelo.api.domain.UserRole;
import com.marcelo.api.exceptions.AppException;
import com.marcelo.api.repository.CarResipository;
import com.marcelo.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    private final CarResipository carResipository;

    @Autowired
    public UserService(UserRepository userRepository, CarResipository carResipository) {
        this.repository = userRepository;
        this.carResipository = carResipository;
    }

    @Transactional
    public User save(User user) {
        this.validateEmailAndLogin(user);
        user.setPassword(this.encryptPassword(user.getPassword()));
        user.setRole(UserRole.USER);
        user.setCreationDate(LocalDate.now());
        if (user.getCars() != null) {
            user.getCars().forEach(carResipository::save);
        }
        return this.repository.save(user);
    }

    private String encryptPassword(String passWord) {
        return new BCryptPasswordEncoder().encode(passWord);
    }

    public List<User> findAll() {
        return this.repository.findAll();
    }

    public User findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public void delete(Long id) {
        this.carResipository.findCarsByUserId(id)
                .forEach(this.carResipository::delete);
        this.repository.delete(this.findById(id));
    }

    @Transactional
    public User update(Long id, User userRequest) {
        this.validateEmailAndLogin(userRequest);
        var user = this.findById(id);

        if (userRequest.getCars() != null) {
            userRequest.getCars()
                    .forEach(this.carResipository::save);
        }

        return this.repository.save(userRequest);
    }

    private void validateEmailAndLogin(User user) {
        if (this.repository.findByEmail(user.getEmail()) != null) {
            throw new AppException("Email already exists", 2);
        }
        if (this.repository.findByLogin(user.getLogin()) != null) {
            throw new AppException("Login already exists", 3);
        }
    }

    public void updateLastLogin(User user) {
        user.setLastLongin(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        this.repository.save(user);
    }

}
