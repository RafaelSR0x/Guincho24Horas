package com.guincho.repository;

import com.guincho.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByPlacaVeiculo(String placaVeiculo);
}
