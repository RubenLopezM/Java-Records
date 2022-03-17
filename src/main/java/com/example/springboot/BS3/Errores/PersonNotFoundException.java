package com.example.springboot.BS3.Errores;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String mensaje){
        super(mensaje);
    }
}
