package com.example.demo.entitys;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "historia_consulta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String monedaOrigen;
    private String monedaDestino;
    private double monto;
    private double montoConvertido;
    private double tasa;

    @CreationTimestamp 
    @Column (nullable =false, updatable = false)
    private LocalDateTime fechaConsulta;
}
