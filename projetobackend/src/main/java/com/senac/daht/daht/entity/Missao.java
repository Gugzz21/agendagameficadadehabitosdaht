package com.senac.daht.daht.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "missao")
public class Missao {
    @Id
    @GeneratedValue
    @Column(name = "missao_id")
    private int id;
    @Column(name = "missao_repeticao")
    private boolean repeticao;

    @Column(name = "missao_dificuldade")
    private int dificuldade;
    @Column(name = "missao_efeito")
    private int efeito;
    @Column(name = "missao_datainicio")
    private Date datainicio;
    @Column(name = "missao_datafinalizacao")
    private Date datafinalizacao;
    @Column(name = "missao_status")
    private int status;
}
