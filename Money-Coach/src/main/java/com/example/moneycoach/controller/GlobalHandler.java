package com.example.moneycoach.controller;

import com.example.moneycoach.entity.ErrorAnswer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request){
        return ErrorAnswer.builder()
                .exception(ex)
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }

}
