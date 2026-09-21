package com.example.demo.entitys;

import java.time.LocalDate;

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
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Cliente {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String telefono;

    @CreationTimestamp 
    @Column (nullable =false, updatable = false)
    private LocalDate fecha_registro;
    

}
