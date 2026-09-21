package com.example.demo.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ClienteDTO {
    
    //@NotBlank(message = "El nombre del cliente no debe estar vacio")
    @Size (min = 2)
    private String nombre;

    //@NotBlank(message = "El apellido del cliente no debe estar vacio")
    @Size (min = 2)
    private String apellido;

    @NotBlank(message = "El email del cliente no debe estar vacio")
    private String email;

    //@NotBlank(message = "El telefono del cliente no debe estar vacio")
    @Pattern(regexp = "^[0-9]{10}$", message = "El telefono debe contener solo numeros")
    private String telefono; 
}
