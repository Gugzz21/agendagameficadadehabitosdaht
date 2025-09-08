package com.senac.daht.dto.response;

public class TabelaPremioDTOResponse {
    private int id;
    private String nomePremio;
    private String descricaoMissao;

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomePremio() {
        return nomePremio;
    }
    public void setNomePremio(String nomePremio) {
        this.nomePremio = nomePremio;
    }
    public String getDescricaoMissao() {
        return descricaoMissao;
    }
    public void setDescricaoMissao(String descricaoMissao) {
        this.descricaoMissao = descricaoMissao;
    }
}