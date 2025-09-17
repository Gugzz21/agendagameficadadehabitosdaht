package com.senac.daht.dto.response;

import java.time.LocalDate;

public class MissaoDTOResponse {
    private int id;
    private String descricao;
    private int repeticao;
    private int dificuldade;
    private int efeito;
    private LocalDate dataFinalizacao;
    private LocalDate  dataInicio;
    private int status;
    private String nomePersonagem;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public String getNomePersonagem() {
        return nomePersonagem;
    }
    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }
}