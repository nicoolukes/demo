package com.example.demo.exceptions;

public class DescuentoInvalidoException extends RuntimeException{
    
    public DescuentoInvalidoException(String mensaje){
        super(mensaje);
    }
}
