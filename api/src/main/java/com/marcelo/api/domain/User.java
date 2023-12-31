package com.marcelo.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    List<Car> cars;

    private UserRole role;

    @NotNull
    private LocalDate creationDate;

    private LocalDateTime lastLongin;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
