package com.senac.daht.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tabelapremio")
public class TabelaPremio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tabelapremio_id")
    private int id;

    private Premio premio;

}
