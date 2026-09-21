package com.example.demo.DTOs;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class VentaEstadisticaDTO {
    private double totalFacturado;
    private int CantidadVentas;
    private double ticketPromedio;
    private VentasEstadicaMeMaDTO ventaMayor;
    private VentasEstadicaMeMaDTO ventaMenor;
    private String productoMasVendido;
     
}
