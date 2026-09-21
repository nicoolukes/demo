package com.example.demo.DTOs;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
public class VentasConDescuentoDTO {
    private List<VentaDescuentoDTO> ventas;
    private double totalConDescuento;
    
}
