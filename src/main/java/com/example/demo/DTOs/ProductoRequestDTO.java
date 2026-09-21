package com.example.demo.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ProductoRequestDTO {
    @NotBlank (message = "El producto no puede estar vacío")
    private String nombre;

    private String categoria;

    @PositiveOrZero (message = "El precio debe ser un valor positivo")
    private Double precio;

    @PositiveOrZero (message = "La cantidad debe ser un valor positivo")
    private int stock;
}
