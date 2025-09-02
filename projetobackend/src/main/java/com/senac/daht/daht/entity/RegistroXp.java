package com.senac.daht.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "registroxp")
public class RegistroXp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "registroxp_id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
