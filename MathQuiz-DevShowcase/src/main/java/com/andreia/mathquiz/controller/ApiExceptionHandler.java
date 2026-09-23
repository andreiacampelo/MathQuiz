package com.andreia.mathquiz.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map> handle404(EntityNotFoundException ex){
        return ResponseEntity.status(404).body(Map.of(
            "status", 404,
            "erro", ex.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map> handle400(MethodArgumentNotValidException ex){
        String msg = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(400).body(Map.of(
            "status", 400,
            "erro", "Bad Request",
            "detalhes", msg
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map> handleBad(IllegalArgumentException ex){
        return ResponseEntity.status(400).body(Map.of(
            "status", 400,
            "erro", ex.getMessage()
        ));
    }
}