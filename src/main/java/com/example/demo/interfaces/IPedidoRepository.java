package com.example.demo.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entitys.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Integer>  {
    
    @Query ( 
        "SELECT DISTINCT p.id FROM Pedido p " +
        "LEFT JOIN p.detalles d " +
        "LEFT JOIN d.productoId pr " +
        "LEFT JOIN pr.categoriaId c " +
        "WHERE (:clienteId IS NULL OR p.clienteId.id = :clienteId) " +
        "AND (:categoria IS NULL OR c.nombre = :categoria) " +
        "AND (:fechaDesde IS NULL OR p.fechaPedido >= :fechaDesde) " +
        "AND (:fechaHasta IS NULL OR p.fechaPedido <= :fechaHasta) " +
        "AND (:estado IS NULL OR p.estado = :estado) " 
    )
    List<Integer> buscarPedidoIds(
        @Param("clienteId") Integer clienteId,
        @Param("categoria") String categoria,
        @Param("fechaDesde") LocalDate fechaDesde,
        @Param("fechaHasta") LocalDate fechaHasta,
        @Param("estado") String estado
    );

    @Query(
        "SELECT DISTINCT p FROM Pedido p " +
        "JOIN FETCH p.clienteId cli " + 
        "LEFT JOIN FETCH p.detalles d " +
        "LEFT JOIN FETCH d.productoId pr " +
        "LEFT JOIN FETCH pr.categoriaId ca " +
        "WHERE p.id IN :ids"
    )
    List<Pedido> buscarPedidoCompleto(@Param("ids") List<Integer> ids);

}
