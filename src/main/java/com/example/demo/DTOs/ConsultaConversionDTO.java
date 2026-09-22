package com.example.demo.DTOs;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ConsultaConversionDTO {
    private LocalDateTime fecha;
    private double tasaCambio;
}
