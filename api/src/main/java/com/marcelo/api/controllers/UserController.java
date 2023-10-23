package com.marcelo.api.controllers;

import com.marcelo.api.domain.User;
import com.marcelo.api.dto.UserDTO;
import com.marcelo.api.dto.UserResponseDTO;
import com.marcelo.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return ResponseEntity.ok(userService
                .findAll()
                .stream()
                .map(UserResponseDTO::toUserResponseDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(UserResponseDTO.toUserResponseDTO(userService.save(user.toUser())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponseDTO.toUserResponseDTO(this.userService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return null;
    }
}
