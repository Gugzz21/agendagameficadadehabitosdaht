package com.senac.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "premio")
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "premio_id")
    private int id;

    @Column(name  = "premio_preco")
    private double preco;

    @Column(name = "premio_nome")
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
