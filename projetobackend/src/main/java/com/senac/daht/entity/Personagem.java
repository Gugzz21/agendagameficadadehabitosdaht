package com.senac.daht.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personagem_id")
    private Integer id;

    @Column(name = "personagem_vida")
    private Double vida;

    @Column(name = "personagem_ouro")
    private Double ouro;

    @Column(name = "personagem_xp")
    private Double xp;

    @OneToOne
    @JoinColumn(name = "registroxp_id", referencedColumnName = "registroxp_id", nullable = false)
    private RegistroXp registroXp;

    @OneToOne
    @JoinColumn(name = "registroouro_id", referencedColumnName = "registrouro_id", nullable = false)
    private RegistroOuro registroOuro;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public Double getOuro() {
        return ouro;
    }

    public void setOuro(Double ouro) {
        this.ouro = ouro;
    }

    public Double getXp() {
        return xp;
    }

    public void setXp(Double xp) {
        this.xp = xp;
    }

    public RegistroXp getRegistroXp() {
        return registroXp;
    }

    public void setRegistroXp(RegistroXp registroXp) {
        this.registroXp = registroXp;
    }

    public RegistroOuro getRegistroOuro() {
        return registroOuro;
    }

    public void setRegistroOuro(RegistroOuro registroOuro) {
        this.registroOuro = registroOuro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
