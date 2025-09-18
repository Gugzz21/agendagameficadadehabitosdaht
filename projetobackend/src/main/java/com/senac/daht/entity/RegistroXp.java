package com.senac.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registroxp")
public class RegistroXp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registroxp_id")
    private Long id;

    @Column(name = "registroxp_quantidade")
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