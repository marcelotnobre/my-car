package com.marcelo.api.controllers;

import com.marcelo.api.domain.User;
import com.marcelo.api.dto.UserSigninDTO;
import com.marcelo.api.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signin")
public class SigninController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<Void> signin(@RequestBody UserSigninDTO user) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
        String token = null;
        try {
            var auth = this.manager.authenticate(userNamePassword);
            token = tokenService.generateToken((User) auth.getPrincipal());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token)
                .build();
    }
}
