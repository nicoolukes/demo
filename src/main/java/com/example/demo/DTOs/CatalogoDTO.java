package com.example.demo.DTOs;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class CatalogoDTO {
    
    private List<ProductoDTO> productos; 
}
