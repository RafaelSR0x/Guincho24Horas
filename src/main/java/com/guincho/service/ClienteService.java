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

    //Criando um chamado
    @Transactional
    public Cliente create(Cliente cliente) {
        var clientPlacaVeiculo = clienteRepository.findByPlacaVeiculo(cliente.getPlacaVeiculo());
        var clientFoneCelular = clienteRepository.findByFoneCelular(cliente.getFoneCelular());

        if (clientPlacaVeiculo !=null || clientFoneCelular !=null) {
            throw new IllegalArgumentException("Dados já cadastrados");
        }

        return clienteRepository.save((cliente));
    }

    //Listar todos os chamados
    @Transactional()
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(ClienteDTO::new).toList();
    }

    //Deleta o usuario através do id e envia os dados exluidos atraves de JSON
    public Cliente delete(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteRepository.deleteById(id);
        return cliente;
    }

    //Atualizar o chamado já existente
    public Cliente update(Cliente cliente) {

    }
}
