package com.example.demo.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class VentaDTO {
    @NotBlank (message = "El producto no puede estar vacío")
    private String nombre;

    @Positive (message = "El precio unitario debe ser un valor positivo")
    private Double precio;

    @Positive (message = "La cantidad debe ser un valor positivo")
    private int cantidad;

    public VentaDTO(String nombre, int cantidad, Double precio) {
        this.nombre= nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public VentaDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPrecio(Double precio){
        this.precio = precio;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }


}
