package com.example.demo.DTOs;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 

public class PedidoDTO {
    
    private int pedidoId;
    private String cliente;
    private LocalDate fecha;
    private String estado;
    private double totalPedido;
    private List<ProductoPedidoDTO> produtos;
}
