package com.marcelo.api;

import com.marcelo.api.dto.MessageDTO;
import com.marcelo.api.exceptions.EmailAlreadyExistsException;
import com.marcelo.api.exceptions.LoginAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {


    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<Object> emailAlreadyExists(EmailAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new MessageDTO("Email already exists", 2));
    }

    @ExceptionHandler(value = LoginAlreadyExistsException.class)
    public ResponseEntity<Object> loginAlreadyExists(LoginAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new MessageDTO("Login already exists", 3));
    }
}
