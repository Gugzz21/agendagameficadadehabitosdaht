package com.senac.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registrouro")
public class RegistroOuro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registrouro_id")
    private int id;

    @Column(name = "registrouro_quantidade")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "personagem_id", referencedColumnName = "personagem_id")
    private Personagem personagem;

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

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
}
