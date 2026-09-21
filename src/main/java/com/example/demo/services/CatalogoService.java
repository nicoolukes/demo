package com.example.demo.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.DTOs.CatalogoDTO;
import com.example.demo.DTOs.FiltroDTO;
import com.example.demo.DTOs.ProductoDTO;
import com.example.demo.DTOs.ProductoRequestDTO;
import com.example.demo.exceptions.ProductoNoEncontrado;
import com.example.demo.exceptions.StockInvalido;

import jakarta.annotation.PostConstruct;



@Service 
public class CatalogoService {

    private final List<ProductoDTO> productos = new ArrayList<>();

    @PostConstruct 
    private void productoCargados(){
        productos.add(new ProductoDTO(1, "Teclado Mecánico", "Perifericos", 45000.0, 15));
        productos.add(new ProductoDTO(2, "Mouse Gamer", "Perifericos", 22000.0, 30));
        productos.add(new ProductoDTO(3, "Monitor 24 pulgadas", "Pantallas", 180000.0, 10));
        productos.add(new ProductoDTO(4, "Auriculares Inalámbricos", "Audio", 35000.0, 25));
        productos.add(new ProductoDTO(5, "Notebook i5", "Computadoras", 650000.0, 5));
        productos.add(new ProductoDTO(6, "Pendrive 64GB", "Almacenamiento", 9000.0, 50));
        productos.add(new ProductoDTO(7, "Silla Gamer", "Mobiliario", 210000.0, 8));
        productos.add(new ProductoDTO(8, "Pad Mouse XL", "Perifericos", 8500.0, 40));
    }

    
    public CatalogoDTO obtenerCatalogo(FiltroDTO filtros){
        List<ProductoDTO> resultado = productos.stream()
            .filter(p-> filtros.getCategoria() == null || p.getCategoria().equalsIgnoreCase(filtros.getCategoria()))
            .filter(p-> filtros.getPrecioMin() == null || p.getPrecio() >= filtros.getPrecioMin())
            .filter(p-> filtros.getPrecioMax() == null || p.getPrecio() <= filtros.getPrecioMax())
            .collect(Collectors.toList())
        ;

        if(filtros.getCriterio() != null && !filtros.getCriterio().isEmpty()){
            Comparator<ProductoDTO> comparador = null;

            if(filtros.getCriterio().equalsIgnoreCase("precio")){
                comparador = Comparator.comparing(ProductoDTO:: getPrecio);
            }

            if(filtros.getCriterio().equalsIgnoreCase("nombre")){
                comparador = Comparator.comparing(ProductoDTO:: getNombre);
            }

            if(comparador != null){
                if(filtros.getOrden().equalsIgnoreCase("desc")){
                    comparador = comparador.reversed();
                }

                resultado = resultado.stream().sorted(comparador).collect(Collectors.toList());

            }
        }
        
        return new CatalogoDTO(resultado);
        
    }

    public ProductoDTO cargarProductos(ProductoRequestDTO productoCargar){

        int idNuevo = generarNumeroRandom();
        ProductoDTO producto = new ProductoDTO (idNuevo, productoCargar.getNombre(), productoCargar.getCategoria(), productoCargar.getPrecio(), productoCargar.getStock());
        productos.add(producto);

        return producto;
    }

    public ProductoDTO modificarProducto(int id, int stock){

        ProductoDTO producto = productos.stream().filter(p ->  p.getId() == id).findFirst().orElse(null);

        if(producto == null){
            throw new ProductoNoEncontrado("No existe un producto con ese id");
        }

        if(producto.getStock() + stock < 0){
            throw new StockInvalido("La cantidad de stock a restar es mayor a la cantidad guardad");
        }

        producto.setStock(producto.getStock() + stock);

        return producto;
    }

    public void eliminarProductoById(int id){

        ProductoDTO producto = productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if(producto == null){
            throw new ProductoNoEncontrado("No existe un producto con ese id");
        }
        
        productos.remove(producto);

        
    }

    private int generarNumeroRandom(){
        Random random = new Random();
        boolean existe = true;
        int idNuevo = 0;

        while(existe == true){
            idNuevo = random.nextInt(100);
            final int idGenerado = idNuevo;
            existe = productos.stream().anyMatch(p -> p.getId() == idGenerado);
        }
        return idNuevo;
    
    }
}
