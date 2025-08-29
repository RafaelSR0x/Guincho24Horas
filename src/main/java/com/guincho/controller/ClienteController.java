package com.guincho.controller;

import com.guincho.dto.ClienteDTO;
import com.guincho.entities.Cliente;
import com.guincho.repository.ClienteRepository;
import com.guincho.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/chamados")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/criar")
    public ResponseEntity entradaVeiculo(@RequestBody Cliente cliente) {

        var clientPlacaVeiculo = clienteRepository.findByPlacaVeiculo(cliente.getPlacaVeiculo());
        var clientFoneCelular = clienteRepository.findByFoneCelular(cliente.getFoneCelular());

        if (clientPlacaVeiculo !=null || clientFoneCelular !=null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Dados já cadastrados");
        }

        Cliente novoCliente = clienteService.create(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(clienteService.findAll());
    }
}
