package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class FiltroDTO {
    private String categoria;
    private Double precioMin;
    private Double precioMax;
    private String criterio;
    private String orden = "asc"; 
}
