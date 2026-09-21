package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.APIResponse;
import com.example.demo.DTOs.ClienteDTO;
import com.example.demo.entitys.Cliente;
import com.example.demo.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @Operation (
        summary= "Crea un cliente y lo devuelve",
        description= "Recibe un cliente, lo crea y devuelve el cliente creado"
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="El cliente fue creado con exito"),
        @ApiResponse(responseCode="400", description="El tipo del campo es invalido")

    })

    @PostMapping
    public ResponseEntity<APIResponse<Cliente>> cargarCliente(@RequestBody @Valid ClienteDTO cliente){

        Cliente clienteGuardado = clienteService.cargarCliente(cliente);
        
        return ResponseEntity.ok(new APIResponse<>(201, "Cliente creado con exito", clienteGuardado));
    }

    
}
