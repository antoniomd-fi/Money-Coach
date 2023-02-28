package com.example.moneycoach.entity.builders;

import com.example.moneycoach.entity.ErrorAnswer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;
public class ErrorAnswerBuilder {

    private int status;
    private Map<String,String> errores;
    private String ruta;

    public ErrorAnswerBuilder estatus(int estatus) {
        this.status = estatus;
        return this;
    }

    public ErrorAnswerBuilder status(HttpStatus estatus) {
        this.status = estatus.value();
        return this;
    }

    public ErrorAnswerBuilder errores(Map<String, String> error) {
        this.errores = error;
        return this;
    }

    public ErrorAnswerBuilder  exception(MethodArgumentNotValidException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();

        this.errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errores.put(fieldName, errorMessage);
        });

        return this;
    }

    public ErrorAnswerBuilder ruta(String ruta) {
        this.ruta = ruta;
        return this;
    }

    public ErrorAnswer build(){
        ErrorAnswer respuesta  = new ErrorAnswer();

        respuesta.setStatus(this.status);
        respuesta.setErrores(this.errores);
        respuesta.setRuta(this.ruta);

        return respuesta;
    }

    public ResponseEntity<ErrorAnswer> entidad(){
        return ResponseEntity.status(status).headers(HttpHeaders.EMPTY).body(build());
    }
}
