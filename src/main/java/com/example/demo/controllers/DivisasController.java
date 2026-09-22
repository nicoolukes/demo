package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.demo.DTOs.APIResponse;
import com.example.demo.DTOs.ConversionDTO;
import com.example.demo.DTOs.ConsultaConversionDTO;
import com.example.demo.services.DivisasServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/divisas")
public class DivisasController {
    
    private final DivisasServices divisasServices;

    public DivisasController(DivisasServices divisasServices){
        this.divisasServices = divisasServices;
    }

    @Operation (
        summary= "Convierte un monto de una divisa a otra",
        description= "Recibe un monto, una divisa de origen y una divisa de destino, y devuelve el monto convertido a la divisa de destino"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Conversión realizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de entrada inválidos")
    })
    @GetMapping ("/convercion")
    public ResponseEntity<APIResponse<ConversionDTO>> conversion(@RequestParam double monto, @RequestParam String origen, @RequestParam String destino){

        ConversionDTO conversion = divisasServices.conversorDivisas(monto, origen, destino);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Se realizó la conversión correctamente", conversion));
    }

    @Operation (
        summary= "Convierte un monto de una divisa a otra y gurada la consulta",
        description= "Recibe un monto, una divisa de origen y una divisa de destino, y devuelve el monto convertido a la divisa de destino, ademas guarda la consulta en la base de datos"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Conversión realizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<APIResponse<ConversionDTO>> consultar(@RequestParam String origen, @RequestParam String destino, @RequestParam double monto){
        ConversionDTO conversion = divisasServices.consultar(monto, origen, destino);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Se realizó la conversión correctamente", conversion));
    }

    @Operation (
        summary= "Recive una consulta, la busca en la base de datos y la devuelve",
        description= "Recibe una consulta con la divisa de origen y la divisa de destino, busca esa consulta en la base de datos y devuelve la consulta"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de entrada inválidos")
    })
    @GetMapping ("/historial")
    public ResponseEntity<APIResponse<List<ConsultaConversionDTO>>> buscarHistorial(@RequestParam String origen, @RequestParam String destino){

        List<ConsultaConversionDTO> consulta = divisasServices.buscarHistorial(origen, destino);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Consulta realizada correctamente", consulta));
    }
}
