package com.guincho.service;

import com.guincho.dto.ClienteDTO;
import com.guincho.entities.Cliente;
import com.guincho.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente create(Cliente cliente) {

        return clienteRepository.save((cliente));
    }

    @Transactional()
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(ClienteDTO::new).toList();
    }
}
