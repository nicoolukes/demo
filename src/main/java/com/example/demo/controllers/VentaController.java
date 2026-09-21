package com.example.demo.controllers;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.APIResponse;
import com.example.demo.DTOs.VentaDTO;
import com.example.demo.DTOs.VentaEstadisticaDTO;
import com.example.demo.DTOs.VentasConDescuentoDTO;
import com.example.demo.services.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }
    
    @Operation (
        summary= "Calcula las estadicas de un listado de ventas",
        description= "Recibe una lista de ventas y devuelve total facturado, ticket promedio, la venta de mayor y menor monto, y el producto mas vendido."
    )

    @ApiResponses({
        @ApiResponse(responseCode="200", description="Estadisticas calculadas con exito"),
        @ApiResponse(responseCode="400", description="Datos invalidos o lista vacia")

    })

    @PostMapping("/estadisticas")
    public ResponseEntity<APIResponse<VentaEstadisticaDTO>> obtenerEstadistica(@RequestBody List<@Valid VentaDTO> ventas){
        VentaEstadisticaDTO data = ventaService.calcularEstadisticas(ventas);

        return ResponseEntity.ok(new APIResponse<>( HttpStatus.OK.value(), "Estadísticas obtenidas correctamente", data));
    }


    @Operation (
        summary= "Aplica descuento a cada venta de la lista",
        description= "Recibe una lista de ventas y un porcentaje de descuento, aplica el descuento a cada venta y devuelve la venta con el monto total con el descuento a cada venta y el total de todo con el descuento aplicado "
    )

    @ApiResponses({
        @ApiResponse(responseCode="200", description="Descuento aplicado con exito"),
        @ApiResponse(responseCode="400", description="Lista vacia o descuento invalido")

    })

    @PostMapping("/descuentos")
    public ResponseEntity<APIResponse<VentasConDescuentoDTO>> postMethodName(@RequestBody List<@Valid VentaDTO> ventas, @RequestParam double descuento) {
        VentasConDescuentoDTO data = ventaService.calcularDescuento(ventas, descuento);
        
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Descuento aplicado con exito", data)) ;
    }
    
}
