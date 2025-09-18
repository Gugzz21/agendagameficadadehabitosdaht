package com.senac.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registroouro")
public class RegistroOuro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registroouro_id")
    private Long id;

    @Column(name = "registroouro_quantidade")
    private Double quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}