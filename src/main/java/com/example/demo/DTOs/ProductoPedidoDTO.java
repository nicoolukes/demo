package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor 
public class ProductoPedidoDTO {
   private String nombre;
   private String categoria;
   private double subtotal;
   private int cantidad; 
}
