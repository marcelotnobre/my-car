package com.marcelo.api.repository;

import com.marcelo.api.domain.Car;
import com.marcelo.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

    @Query("SELECT c FROM User u JOIN u.cars c WHERE c.id = :carId AND u.id = :userId")
    Car findCarByCarIdAndUserId(@Param("carId") Long carId, @Param("userId") Long userId);
}
