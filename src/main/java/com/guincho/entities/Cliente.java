package com.guincho.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String placaVeiculo;
    private String nomeCliente;

    @Column(unique = true)
    private Integer foneCelular;
    private String endereco;
    private String cidade;

    public Cliente() {

    }

    public Cliente(Long id, String placaVeiculo, String nomeCliente, Integer foneCelular, String endereco, String cidade) {
        this.id = id;
        this.placaVeiculo = placaVeiculo;
        this.nomeCliente = nomeCliente;
        this.foneCelular = foneCelular;
        this.endereco = endereco;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getFoneCelular() {
        return foneCelular;
    }

    public void setFoneCelular(Integer foneCelular) {
        this.foneCelular = foneCelular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
