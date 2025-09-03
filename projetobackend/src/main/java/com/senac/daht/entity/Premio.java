package com.senac.daht.entity;

// src/main/java/com/senac/daht/entity/Premio.java

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "premio")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "premio_id")
    private int id;

    @Column(name = "premio_preco")
    private Double preco;

    @Column(name = "premio_nome")
    private String nome;

    @OneToMany(mappedBy = "premio")
    private Set<TabelaPremio> tabelapremios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<TabelaPremio> getTabelapremios() {
        return tabelapremios;
    }

    public void setTabelapremios(Set<TabelaPremio> tabelapremios) {
        this.tabelapremios = tabelapremios;
    }
}
