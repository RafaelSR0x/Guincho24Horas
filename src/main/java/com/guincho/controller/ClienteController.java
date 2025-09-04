package com.guincho.controller;

import com.guincho.dto.ClienteDTO;
import com.guincho.entities.Cliente;
import com.guincho.repository.ClienteRepository;
import com.guincho.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity criarChamado(@RequestBody Cliente cliente) {

        try {
            Cliente novoCliente = clienteService.create(cliente);
            return ResponseEntity.ok(novoCliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletarChamado(@RequestBody Cliente cliente) {
        try {
            Cliente deletarCliente = clienteService.delete(cliente.getId());
            return ResponseEntity.ok(deletarCliente);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity atualizarChamado(@PathVariable Long id, @RequestBody Cliente cliente) {
     try {
         Cliente atualizarCliente = clienteService.update(id, cliente);
         return ResponseEntity.ok(atualizarCliente);
     }catch (IllegalArgumentException e) {
         return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
     }catch (RuntimeException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
     }
    }

}
