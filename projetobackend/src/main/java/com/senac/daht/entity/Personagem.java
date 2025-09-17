package com.senac.daht.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "personagem")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personagem_id")
    private Long id;

    @Column(name = "personagem_vida")
    private int vida;

    @Column(name = "personagem_ouro")
    private int ouro;

    @Column(name = "personagem_xp")
    private int xp;

    @OneToOne
    @JoinColumn(name = "registroouro_id", referencedColumnName = "registroouro_id", nullable = false)
    private RegistroOuro registroOuro;


    @OneToOne
    @JoinColumn(name = "registroxp_id", referencedColumnName = "registroxp_id", nullable = false)
    private RegistroXp registroXp;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id", nullable = false)
    private Usuario usuario;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getOuro() {
        return ouro;
    }

    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
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
