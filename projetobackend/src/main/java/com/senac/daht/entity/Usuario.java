package com.senac.daht.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private int id;

    @Column(name = "usuario_nome")
    private String nome;

    @Column(name = "usuario_email")
    private String email;

    @Column(name = "usuario_telefone")
    private String telefone;

    @Column(name = "usuario_datanascimento")
    private Date datanascimento;

    @Column(name = "usuario_senha")
    private String senha;

    @Column(name = "usuario_status")
    private int status;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Personagem personagem;

    public Usuario(String nome, String email, String telefone, Date datanascimento, String senha, Integer status) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.datanascimento = datanascimento;
        this.senha = senha;
        this.status = status;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
