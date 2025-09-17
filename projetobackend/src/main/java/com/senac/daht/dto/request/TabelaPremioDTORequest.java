package com.senac.daht.dto.request;

import jakarta.validation.constraints.NotNull;

public class TabelaPremioDTORequest {
    @NotNull
    private Integer premioId; // Chave estrangeira

    // Getters e Setters
    public Integer getPremioId() {
        return premioId;
    }
    public void setPremioId(Integer premioId) {
        this.premioId = premioId;
    }

}