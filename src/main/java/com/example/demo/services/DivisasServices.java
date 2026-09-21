package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.DTOs.ConversionDTO;
import com.example.demo.DTOs.FrankfurterResponseDTO;
import com.example.demo.exceptions.DatosInvalidosExceptions;
import com.example.demo.exceptions.ServicioExternoException;

@Service 
public class DivisasServices {
    
    private RestClient restClient;

    public DivisasServices(RestClient restClient){
        this.restClient = restClient;
    }

    public ConversionDTO conversorDivisas(double monto, String origen, String destino){

        if(monto < 0){
            throw new DatosInvalidosExceptions("El monto debe ser mayor a 0");
        }

        if(origen == null || !origen.matches("[A-Za-z]{3}")){
            throw new DatosInvalidosExceptions("La moneda de origen debe ser un codigode 3 letras");
        }

        if(destino == null || !destino.matches("[A-Za-z]{3}")){
            throw new DatosInvalidosExceptions("La moneda de destino debe ser un codigo 3 letras");
        }

        origen = origen.toUpperCase();
        destino = destino.toUpperCase();

        try {
            FrankfurterResponseDTO respuesta = restClient
                .get()
                .uri("/v2/rate/{origen}/{destino}", origen, destino)
                .retrieve()
                .body(FrankfurterResponseDTO.class);
            
            double tasa = respuesta.getRate();
            double montoConvertido = monto * tasa;

            return new ConversionDTO( monto, origen, destino, tasa, montoConvertido, respuesta.getDate());
        } catch (Exception e) {
            throw new ServicioExternoException("No se pudo obtener la informacion de la API de furter", e);
        }


    }
}

