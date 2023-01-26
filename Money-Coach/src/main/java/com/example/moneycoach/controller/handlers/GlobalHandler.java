package com.example.moneycoach.controller.handlers;

import com.example.moneycoach.entity.ErrorAnswer;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.TreeMap;

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request){
        return ErrorAnswer.builder()
                .exception(ex)
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }*/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request){
        Map<String,String> errors = new TreeMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()){
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }

        ErrorAnswer errorAnswer = new ErrorAnswer();
        errorAnswer.setErrores(errors);
        errorAnswer.setRuta(request.getDescription(false).substring(4));
        return handleExceptionInternal(ex,errorAnswer,headers, HttpStatus.BAD_REQUEST, request);
    }

}
