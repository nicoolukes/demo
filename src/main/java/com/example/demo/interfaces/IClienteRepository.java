package com.example.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitys.Cliente;

public interface  IClienteRepository extends JpaRepository<Cliente, Integer> {
    
    boolean existsByEmail(String email);
}
