package com.example.demo.DTOs;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class VentasEstadicaMeMaDTO {
    
    private String nombreProduco;
    private double precioUnitario;
    private int cantidad;
    private double importe;
}
