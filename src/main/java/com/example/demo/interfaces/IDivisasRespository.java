package com.example.demo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entitys.HistoriaConversion;

public interface IDivisasRespository extends JpaRepository<HistoriaConversion, Integer> {
    @Query("SELECT h FROM HistoriaConversion h " +
        "WHERE (h.monedaOrigen = :origen AND h.monedaDestino = :destino ) " +
        "ORDER BY h.fechaConsulta DESC"
    )
    List<HistoriaConversion> buscarConsulta(@Param("origen") String origen, @Param("destino") String destino);
}
