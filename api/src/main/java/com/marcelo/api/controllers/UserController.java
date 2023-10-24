package com.marcelo.api.controllers;

import com.marcelo.api.domain.User;
import com.marcelo.api.dto.UserDTO;
import com.marcelo.api.dto.UserResponseDTO;
import com.marcelo.api.dto.UserUpdateDTO;
import com.marcelo.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(this.service
                .findAll()
                .stream()
                .map(UserResponseDTO::toUserResponseDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserDTO userRequest) {
        var user = userRequest.toUser();
        user.setCreationDate(LocalDate.now());
        return ResponseEntity.ok(UserResponseDTO.toUserResponseDTO(this.service.save(user)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponseDTO.toUserResponseDTO(this.service.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userRequest) {
        var user = userRequest.toUser();
        user.setId(id);
        return ResponseEntity.ok(UserResponseDTO.toUserResponseDTO(this.service.update(user)));
    }
}
