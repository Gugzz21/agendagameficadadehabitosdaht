package com.senac.daht.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "missao")
public class Missao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missao_id")
    private int id;

    @Column(name = "missao_descricao")
    private String descricao;

    @Column(name = "missao_repeticao")
    private int repeticao;

    @Column(name = "missao_dificuldade")
    private int dificuldade;

    @Column(name = "missao_efeito")
    private int efeito;

    @Column(name = "missao_datafinalizacao")
    private LocalDate dataFinalizacao;

    @Column(name = "missao_datainicio")
    private LocalDate  dataInicio;

    @Column(name = "missao_status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "personagem_id", referencedColumnName = "personagem_id")
    private Personagem personagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getEfeito() {
        return efeito;
    }

    public void setEfeito(int efeito) {
        this.efeito = efeito;
    }

    public LocalDate  getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDate  dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public LocalDate  getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate  dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
}