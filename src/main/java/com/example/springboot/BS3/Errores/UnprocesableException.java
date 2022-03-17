package com.example.springboot.BS3.Errores;

public class UnprocesableException extends RuntimeException {
    public UnprocesableException(String mensaje){
        super(mensaje);
    }
}
