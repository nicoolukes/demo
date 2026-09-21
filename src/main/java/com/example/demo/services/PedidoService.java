package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entitys.Pedido;
import com.example.demo.entitys.DetallePedido;
import com.example.demo.interfaces.IPedidoRepository;
import com.example.demo.DTOs.ProductoPedidoDTO;
import com.example.demo.DTOs.PedidoDTO;

@Service
public class PedidoService {
   
    private final IPedidoRepository pedidoRepository;

    public PedidoService(IPedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public List<ProductoPedidoDTO> buscarPedidos(Integer clienteId, String categoria, LocalDate fechaDesde, LocalDate fechaHasta, String estado){
        
        List<Integer> ids = pedidoRepository.buscarPedidoIds(clienteId, categoria, fechaDesde, fechaHasta, estado);
        List<Pedido> pedidoCompleto = pedidoRepository.buscarPedidoCompleto(ids);

        List<PedidoDTO> pedido = pedidoCompleto.stream().map(p -> convertirPedido(p)).toList();

        return pedido;
    }

    private PedidoDTO convertirPedido(Pedido pedido){
       
        PedidoDTO dto = new PedidoDTO();
        dto.setCliente(pedido.getClienteId().getNombre());
        dto.setPedidoId(pedido.getId());
        dto.setFecha(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado());
        
        List<ProductoPedidoDTO> productos = pedido.getDetalles().stream().map(d -> convertirProducto(d)).toList();

        dto.setProdutos(productos);

        double total = productos.stream().mapToDouble(ProductoPedidoDTO::getSubtotal).sum();

        dto.setTotalPedido(total);

        return dto;



    }

    private ProductoPedidoDTO convertirProducto(DetallePedido detalle){

        ProductoPedidoDTO dto = new ProductoPedidoDTO();
        dto.setNombre(detalle.getProductoId().getNombre());
        dto.setCategoria(detalle.getProductoId().getCategoriaId().getNombre());
        dto.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());
        dto.setCantidad(detalle.getCantidad());

        return dto;
    }
}
