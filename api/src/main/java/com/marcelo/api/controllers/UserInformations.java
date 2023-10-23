package com.marcelo.api.controllers;

import com.marcelo.api.domain.User;
import com.marcelo.api.dto.UserDTO;
import com.marcelo.api.dto.UserResponseDTO;
import com.marcelo.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
public class UserInformations {

    private final UserService service;

    @Autowired
    public UserInformations(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> find(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(UserResponseDTO.toUserResponseDTO(service.findById(user.getId())));

    }
}
