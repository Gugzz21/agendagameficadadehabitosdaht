package com.senac.daht.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "personagem")
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personagem_id")
    private int id;

    @Column(name = "personagem_vida")
    private double vida;

    @Column(name = "personagem_ouro")
    private double ouro;

    @Column(name = "personagem_xp")
    private int xp;

    @Column(name = "registrouro_id")
    private int registrouroId;

    @Column(name = "registroxp_id")
    private int registroxpId;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private List<Missao> missoes;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private List<RegistroOuro> registroOuros;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private List<RegistroXp> registroXps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getOuro() {
        return ouro;
    }

    public void setOuro(double ouro) {
        this.ouro = ouro;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getRegistrouroId() {
        return registrouroId;
    }

    public void setRegistrouroId(int registrouroId) {
        this.registrouroId = registrouroId;
    }

    public int getRegistroxpId() {
        return registroxpId;
    }

    public void setRegistroxpId(int registroxpId) {
        this.registroxpId = registroxpId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Missao> getMissoes() {
        return missoes;
    }

    public void setMissoes(List<Missao> missoes) {
        this.missoes = missoes;
    }

    public List<RegistroOuro> getRegistroOuros() {
        return registroOuros;
    }

    public void setRegistroOuros(List<RegistroOuro> registroOuros) {
        this.registroOuros = registroOuros;
    }

    public List<RegistroXp> getRegistroXps() {
        return registroXps;
    }

    public void setRegistroXps(List<RegistroXp> registroXps) {
        this.registroXps = registroXps;
    }
}