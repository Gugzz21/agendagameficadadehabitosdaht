package com.senac.daht.dto.request;

import jakarta.validation.constraints.NotNull;

public class RegistroOuroDTORequest {
    @NotNull
    private int quantidade;
    @NotNull
    private Integer personagemId; // Chave estrangeira

    // Getters e Setters
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Integer getPersonagemId() {
        return personagemId;
    }
    public void setPersonagemId(Integer personagemId) {
        this.personagemId = personagemId;
    }
}