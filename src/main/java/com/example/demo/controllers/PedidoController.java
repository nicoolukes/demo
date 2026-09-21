package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.APIResponse;
import com.example.demo.entitys.Pedido;
import com.example.demo.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @Operation (
        summary= "Busca pedidos con filtros",
        description= "Recibe filtros o no y busca los pedidos segun los filtros si aplican "
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado de pedidos"),
        @ApiResponse(responseCode = "400", description = "Parámetros de entrada inválidos")
    })

    @GetMapping
    public ResponseEntity<APIResponse<List<Pedido>>> buscarPedidos(@RequestParam(required = false) Integer clienteId,
                                                            @RequestParam(required = false) String categoria,
                                                            @RequestParam(required = false) LocalDate fechaDesde,
                                                            @RequestParam(required = false) LocalDate fechaHasta,
                                                            @RequestParam(required = false) String estado 
        ){
        
        List<Pedido> pedidos = pedidoService.buscarPedidos(clienteId, categoria, fechaDesde, fechaHasta, estado);
        
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Listado de pedidos", pedidos));
    }

}
