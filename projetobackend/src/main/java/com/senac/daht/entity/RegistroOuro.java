package com.senac.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registroouro")
public class RegistroOuro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "registroouro_id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
