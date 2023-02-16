package com.example.bugtracker.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    ResponseEntity<Void> handleResourceNotFoundException(){
        return ResponseEntity.notFound().build();
    }
}
