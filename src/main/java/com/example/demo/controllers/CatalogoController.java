package com.example.demo.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.APIResponse;
import com.example.demo.DTOs.CatalogoDTO;
import com.example.demo.DTOs.FiltroDTO;
import com.example.demo.DTOs.ProductoDTO;
import com.example.demo.DTOs.ProductoRequestDTO;
import com.example.demo.services.CatalogoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService){
        this.catalogoService= catalogoService;
    }

    @Operation (
        summary= "Devuele el catalogo con los productos",
        description= "Se pueden recivir filtros o no, y se devuelve un catalogo con los productos filtrados o no"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Catalogo con los productos"),
        @ApiResponse(responseCode="400", description="El tipo del campo es invalido")

    })
    
    @GetMapping
    public ResponseEntity<APIResponse<CatalogoDTO>> buscarCatalogo(FiltroDTO filtros){ // completar con @
        CatalogoDTO catalogo = catalogoService.obtenerCatalogo(filtros);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Listado de productos", catalogo));
    }


    @Operation (
        summary= "Carga un producto y lo devuelve",
        description= "Se recive un producto, se carga en la lista y se devuelve el producto"
    )
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Producto cargado con exito"),
        @ApiResponse(responseCode="400", description="Datos invalidos")

    })

    @PostMapping("/productos")
    public ResponseEntity<APIResponse<ProductoDTO>> cargarProducto(@RequestBody @Valid ProductoRequestDTO producto){

        ProductoDTO productoResponse = catalogoService.cargarProductos(producto);

        return ResponseEntity.ok(new APIResponse<>(201, "Producto cargado correctamente", productoResponse));
    }


    @Operation (
        summary= "Modifica el stock de un producto",
        description= "Se recive un id de producto y una cantidad, se modifica el stock del producto y se devuelve el producto modificado"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Stock modificado correctamente"),
        @ApiResponse(responseCode="404", description="Producto no encontrado"),
        @ApiResponse(responseCode="400", description="Stock invalido")

    })
    @PutMapping("/{id}/stock")
    public ResponseEntity<APIResponse<ProductoDTO>> modificarStock(@PathVariable int id, @RequestParam int stock) {
        ProductoDTO productoResponse = catalogoService.modificarProducto(id, stock);

        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Stock modificado correctamente", productoResponse));
    }

    @Operation (
        summary= "Elimina un producto",
        description= "Se recive un id de producto y se elimina de la lista"
    )
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Producto eliminado correctamente"),
        @ApiResponse(responseCode="404", description="Producto no encontrado")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> eliminarProducto(@PathVariable int id){

        catalogoService.eliminarProductoById(id);
        return ResponseEntity.ok(new APIResponse<>(HttpStatus.OK.value(), "Producto eliminado correctamente", null));
    }

    
}
