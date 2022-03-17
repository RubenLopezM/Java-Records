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
    public final ResponseEntity<CustomError> handeleNotFoundException(){
        CustomError customError=new CustomError(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<CustomError>(customError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<CustomError> handleUnprocesableException(){
        CustomError customError=new CustomError(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        return new ResponseEntity<CustomError>(customError,HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
