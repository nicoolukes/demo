package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
public class ProductoDTO {
   private int id;
   private String nombre;
   private String categoria;
   private double precio;
   private int stock; 
}
