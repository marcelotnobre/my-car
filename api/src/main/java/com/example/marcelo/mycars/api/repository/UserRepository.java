package com.example.marcelo.mycars.api.repository;

import com.example.marcelo.mycars.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
