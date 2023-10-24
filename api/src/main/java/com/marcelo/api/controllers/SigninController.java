package com.marcelo.api.controllers;

import com.marcelo.api.domain.User;
import com.marcelo.api.dto.MessageDTO;
import com.marcelo.api.dto.UserSigninDTO;
import com.marcelo.api.services.TokenService;
import com.marcelo.api.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    private final AuthenticationManager manager;

    private final TokenService tokenService;

    private final UserService userService;

    @Autowired
    public SigninController(AuthenticationManager manager, TokenService tokenService, UserService userService) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity signin(@RequestBody UserSigninDTO userRequest, HttpServletResponse response) {
        try {
            var userNamePassword = new UsernamePasswordAuthenticationToken(userRequest.getLogin(), userRequest.getPassword());
            var auth = this.manager.authenticate(userNamePassword);
            var user = (User) auth.getPrincipal();
            userService.updateLastLogin(user);

            var token = tokenService.generateToken((User) auth.getPrincipal());
            Cookie cookie = new Cookie("Authentication", token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return ResponseEntity.ok()
                    .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageDTO("Invalid login or password", 1));
        }
    }
}
