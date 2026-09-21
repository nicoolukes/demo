package com.example.demo.entitys;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "pedido")
@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class Pedido{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn (name = "cliente_id", nullable = false)
    private Cliente clienteId;

    @CreationTimestamp 
    @Column (nullable =false, updatable = false)
    private LocalDate fechaPedido;
    
    @Column(nullable = false, length = 100)
    private String estado;

    @OneToMany(mappedBy = "pedidoId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DetallePedido> detalles;
}