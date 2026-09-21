package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class FrankfurterResponseDTO {
    private String date;
    private String base;
    private String quote;
    private double rate;
}
