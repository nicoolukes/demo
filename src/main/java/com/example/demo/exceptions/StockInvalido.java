package com.example.demo.exceptions;

public class StockInvalido extends RuntimeException {
    
    public StockInvalido(String mensaje){
        super(mensaje);
    }
}
