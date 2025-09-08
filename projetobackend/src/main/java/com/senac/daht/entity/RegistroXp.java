package com.senac.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registroxp")
public class RegistroXp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registroxp_id")
    private int id;

    @Column(name = "registroxp_quantidade")
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