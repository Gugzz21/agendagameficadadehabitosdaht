package com.senac.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tabelapremio")
public class TabelaPremio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tabelapremio_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "premio_id", referencedColumnName = "premio_id")
    private Premio premio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }
}