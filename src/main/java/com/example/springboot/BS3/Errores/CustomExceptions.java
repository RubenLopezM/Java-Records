package com.example.springboot.BS3.Errores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<CustomError> handeleNotFoundException(PersonNotFoundException p){
        CustomError customError=new CustomError(new Date(), HttpStatus.NOT_FOUND.value(), p.getMessage());

        return new ResponseEntity<CustomError>(customError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<CustomError> handleUnprocesableException(UnprocesableException u){
        CustomError customError=new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), u.getMessage());
        return new ResponseEntity<CustomError>(customError,HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
