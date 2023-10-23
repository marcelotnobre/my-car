package com.marcelo.api;

import com.marcelo.api.dto.MessageDTO;
import com.marcelo.api.exceptions.AppException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<MessageDTO> emailAlreadyExists(AppException e) {
        return ResponseEntity.badRequest().body(new MessageDTO(e.getMessage(), e.getCodigo()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(new MessageDTO("Invalid fields", 4), HttpStatusCode.valueOf(400));
    }
}
