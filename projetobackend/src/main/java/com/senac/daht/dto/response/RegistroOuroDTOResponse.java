package com.senac.daht.dto.response;

public class RegistroOuroDTOResponse {
    private int id;
    private int quantidade;
    private int idPersonagem;

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getIdPersonagem() {
        return idPersonagem;
    }
    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }
}