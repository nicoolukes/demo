package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;

import com.example.demo.DTOs.ConversionDTO;
import com.example.demo.DTOs.ConsultaConversionDTO;
import com.example.demo.DTOs.FrankfurterResponseDTO;
import com.example.demo.entitys.HistoriaConversion;
import com.example.demo.exceptions.DatosInvalidosExceptions;
import com.example.demo.exceptions.ServicioExternoException;
import com.example.demo.interfaces.IDivisasRespository;

@Service 
public class DivisasServices {
    
    private RestClient restClient;
    private IDivisasRespository divisasRepository;

    public DivisasServices(RestClient restClient, IDivisasRespository divisasRepository){
        this.restClient = restClient;
        this.divisasRepository = divisasRepository;
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

    public ConversionDTO consultar(double monto, String origen, String destino){

        var conversion = conversorDivisas(monto, origen, destino);

       
        HistoriaConversion hc = new HistoriaConversion();
        hc.setMonedaOrigen(conversion.getMonedaOrigen());
        hc.setMonedaDestino(conversion.getMonedaDestino());
        hc.setMonto(conversion.getMontoOrigen());
        hc.setMontoConvertido(conversion.getMontoConvertido());
        hc.setTasa(conversion.getTasaCambio());
        
        divisasRepository.save(hc);
        return conversion;
    }

    public List<ConsultaConversionDTO> buscarHistorial(String monedaOrigen, String monedaDestino){
        if(monedaOrigen == null || !monedaOrigen.matches("[A-Za-z]{3}")){
            throw new DatosInvalidosExceptions("La moneda de origen debe ser un codigode 3 letras");
        }

        if(monedaDestino == null || !monedaDestino.matches("[A-Za-z]{3}")){
            throw new DatosInvalidosExceptions("La moneda de destino debe ser un codigo 3 letras");
        }
        monedaOrigen = monedaOrigen.toUpperCase();
        monedaDestino = monedaDestino.toUpperCase();

        List<HistoriaConversion> historial= divisasRepository.buscarConsulta(monedaOrigen, monedaDestino);

        List<ConsultaConversionDTO> consulta;

        if(historial==null || historial.isEmpty()){
            consulta = List.of();
        }else{  
            consulta = historial.stream().map(h -> convertirToDTO(h)).toList();
        }

        return consulta;
    }

    private ConsultaConversionDTO convertirToDTO(HistoriaConversion h){
        ConsultaConversionDTO c = new ConsultaConversionDTO();
        c.setFecha(h.getFechaConsulta());
        c.setTasaCambio(h.getTasa());

        return c;

    }
}

