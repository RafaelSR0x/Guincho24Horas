package com.guincho.dto;

import com.guincho.entities.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private String placaVeiculo;
    private String nomeCliente;
    private Integer foneCelular;
    private String endereco;
    private String cidade;

    public ClienteDTO(ClienteDTO x) {

    }

    public ClienteDTO(Cliente cliente) {
        placaVeiculo = cliente.getPlacaVeiculo();
        nomeCliente = cliente.getNomeCliente();
        foneCelular = cliente.getFoneCelular();
        endereco = cliente.getEndereco();
        cidade = cliente.getCidade();
    }
}
