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

        if (clientPlacaVeiculo !=null) {
            throw new IllegalArgumentException("Dados já cadastrados");
        }

        return clienteRepository.save(cliente);
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
    public Cliente update(Long id, Cliente clienteAtualizado) {
        //Buscar o usuario pelo id, no caso vai verificar se ele existe no banco de dados


        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // 2. Verificar duplicidade da placa
        var clientePorPlaca = clienteRepository.findByPlacaVeiculo(clienteAtualizado.getPlacaVeiculo());
        if (clientePorPlaca != null && !clientePorPlaca.getId().equals(id)) {
            throw new IllegalArgumentException("Placa já cadastrada para outro cliente");
        }

        cliente.setPlacaVeiculo(clienteAtualizado.getPlacaVeiculo());
        cliente.setNomeCliente(clienteAtualizado.getNomeCliente());
        cliente.setFoneCelular(clienteAtualizado.getFoneCelular());

        return clienteRepository.save(cliente);

        /*var clientPlacaVeiculo = clienteRepository.findByPlacaVeiculo(clienteAtualizado.getPlacaVeiculo());
        var clientFoneCelular = clienteRepository.findByFoneCelular(clienteAtualizado.getFoneCelular());

        if (clientPlacaVeiculo !=null || clientFoneCelular !=null) {
            throw new IllegalArgumentException("Dados já cadastrados");
        }*/
    }
}
