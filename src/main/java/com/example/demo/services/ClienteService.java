package com.example.demo.services;
import org.springframework.stereotype.Service;

import com.example.demo.DTOs.ClienteDTO;
import com.example.demo.entitys.Cliente;
import com.example.demo.exceptions.EmailExistenteException;
import com.example.demo.interfaces.IClienteRepository;

@Service
public class ClienteService {
    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente cargarCliente(ClienteDTO clienteDto){

        if(clienteRepository.existsByEmail(clienteDto.getEmail())){
            throw new EmailExistenteException("El email ya esta registrado");
        }

        Cliente cliente = new Cliente();

        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefono(clienteDto.getTelefono());
        

        return clienteRepository.save(cliente);
        
    }
}
