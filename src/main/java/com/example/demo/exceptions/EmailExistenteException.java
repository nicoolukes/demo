package com.example.demo.exceptions;

public class EmailExistenteException extends RuntimeException {
    
    public EmailExistenteException(String mensaje){
        super(mensaje);
    }
}
