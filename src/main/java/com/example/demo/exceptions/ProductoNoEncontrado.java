package com.example.demo.exceptions;

public class ProductoNoEncontrado  extends RuntimeException{

    public ProductoNoEncontrado(String mensaje){
        super(mensaje);
    }
    
}
