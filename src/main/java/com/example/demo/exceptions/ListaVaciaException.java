package com.example.demo.exceptions;


public class ListaVaciaException extends RuntimeException {
    
    public ListaVaciaException(String mensaje){
        super(mensaje);
    }
}
