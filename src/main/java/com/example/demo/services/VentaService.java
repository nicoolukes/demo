package com.example.demo.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.DTOs.VentaDTO;
import com.example.demo.DTOs.VentaDescuentoDTO;
import com.example.demo.DTOs.VentaEstadisticaDTO;
import com.example.demo.DTOs.VentasConDescuentoDTO;
import com.example.demo.DTOs.VentasEstadicaMeMaDTO;
import com.example.demo.exceptions.ListaVaciaException;
import com.example.demo.exceptions.DescuentoInvalidoException;

@Service
public class VentaService{

    public VentaEstadisticaDTO calcularEstadisticas(List<VentaDTO> ventas){
        double totalFacturado = 0;

        if(ventas == null || ventas.isEmpty()) {
            throw new ListaVaciaException("La lista de ventas esta vacia");
        }

        VentaDTO ventaMayor= ventas.get(0);
        VentaDTO ventaMenor= ventas.get(0);

        Map<String, Integer> cantidadXProducto = new HashMap<>();

        for(VentaDTO venta : ventas){

            double importe = calcularImporte(venta);
            totalFacturado += importe;

            if(importe > calcularImporte(ventaMayor)){
                ventaMayor = venta;
            }

            if(importe < calcularImporte(ventaMenor)){
                ventaMenor = venta;
            }

            cantidadXProducto.put(
                venta.getNombre(),
                cantidadXProducto.getOrDefault(venta.getNombre(), 0) + venta.getCantidad()
            );
        }

        int cantidadVentas = ventas.size();

        double ticketPromedio = totalFacturado / cantidadVentas;

        String productoMasVendido = cantidadXProducto.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

        VentasEstadicaMeMaDTO mayor = convertirVenta(ventaMayor);
        VentasEstadicaMeMaDTO menor = convertirVenta(ventaMenor);

        return new VentaEstadisticaDTO(totalFacturado, cantidadVentas, ticketPromedio, mayor, menor, productoMasVendido);
    }

    public VentasConDescuentoDTO calcularDescuento(List<VentaDTO> ventas, double descuento){
        double totalConDescuento= 0;

        if(ventas == null || ventas.isEmpty()) {
            throw new ListaVaciaException("La lista de ventas esta vacia");
        }

        if(descuento < 0 || descuento > 100){
            throw new DescuentoInvalidoException("El descuento tiene que estar entre 0 y 100");
        }

        List<VentaDescuentoDTO> ventaDescuento = new ArrayList<>();

        for (VentaDTO venta : ventas){
            double monto = calcularImporte(venta);
            double montoDescuento = monto - (monto * descuento / 100);
            ventaDescuento.add( new VentaDescuentoDTO(venta.getNombre(), venta.getPrecio(), venta.getCantidad(), montoDescuento));
            totalConDescuento += montoDescuento;  

        }

        return new VentasConDescuentoDTO(ventaDescuento, totalConDescuento);
    }

    private double calcularImporte(VentaDTO venta){
        return venta.getPrecio() * venta.getCantidad();
    }

    private VentasEstadicaMeMaDTO convertirVenta(VentaDTO venta){
        double importe = calcularImporte(venta);

        return new VentasEstadicaMeMaDTO(venta.getNombre(), venta.getPrecio(), venta.getCantidad(), importe );
    }
}