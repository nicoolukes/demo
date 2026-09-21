package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VentaDescuentoDTO {

    private String nombre;
    private Double precio;
    private int cantidad;
    private Double montoConDescuento;
}
