package com.marcelo.api.services;

import com.marcelo.api.domain.Car;
import com.marcelo.api.domain.User;
import com.marcelo.api.domain.UserRole;
import com.marcelo.api.repository.CarResipository;
import com.marcelo.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final CarResipository carResipository;

    @Autowired
    public UserService(UserRepository userRepository, CarResipository carResipository) {
        this.userRepository = userRepository;
        this.carResipository = carResipository;
    }

    @Transactional
    public User save(User user) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(UserRole.USER);
        user.setCreationDate(LocalDate.now());
        user.getCars().forEach(carResipository::save);
        return this.userRepository.save(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void delete(Long id) {
        this.userRepository.delete(this.findById(id));
    }

    public User update(Long id, User userRequest) {
        User user = this.findById(id);
        return null;
    }
}
