package com.example.marcelo.mycars.api.controllers;

import com.example.marcelo.mycars.api.dto.UserSigninDTO;
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
    AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<Void> signin(@RequestBody UserSigninDTO user) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
       try {
           var auth = this.manager.authenticate(userNamePassword);
           boolean teste = auth.isAuthenticated();
           System.out.println("");
       }catch (Exception e) {

       }
        return ResponseEntity.ok().build();
    }
}
