package com.example.demo.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table (name = "detalle_pedido")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class DetallePedido {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn (name = "pedido_id", nullable = false)
    private Pedido pedidoId;

    @ManyToOne
    @JoinColumn (name = "producto_id", nullable = false)
    private Producto productoId;

    @Column(nullable = false)
    private int cantidad;
    
    @Column(nullable = false)
    private double precioUnitario;
}
