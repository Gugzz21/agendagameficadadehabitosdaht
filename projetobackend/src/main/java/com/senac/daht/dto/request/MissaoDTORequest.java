package com.senac.daht.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissaoDTORequest {
    @NotBlank
    private String descricao;
    @NotNull
    private int repeticao;
    @NotNull
    private int dificuldade;
    @NotNull
    private int efeito;
    private LocalDate dataFinalizacao;
    private LocalDate  dataInicio;
    @NotNull
    private int status;
    @NotNull
    private Integer personagemId; // Chave estrangeira

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getRepeticao() {
        return repeticao;
    }
    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }
    public int getDificuldade() {
        return dificuldade;
    }
    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }
    public int getEfeito() {
        return efeito;
    }
    public void setEfeito(int efeito) {
        this.efeito = efeito;
    }
    public LocalDate  getDataFinalizacao() {
        return dataFinalizacao;
    }
    public void setDataFinalizacao(LocalDate  dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
    public LocalDate  getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate  dataInicio) {
        this.dataInicio = dataInicio;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Integer getPersonagemId() {
        return personagemId;
    }
    public void setPersonagemId(Integer personagemId) {
        this.personagemId = personagemId;
    }
}