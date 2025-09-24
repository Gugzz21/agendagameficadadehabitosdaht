
package com.senac.daht.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personagem")
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personagem_id")
    private Long id;

    @Column(name = "personagem_vida")
    private Double vida;

    @Column(name = "personagem_ouro")
    private Double ouro;

    @Column(name = "personagem_xp")
    private Double xp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registroouro_id", referencedColumnName = "registroouro_id", nullable = false)
    private RegistroOuro registroOuro;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public RegistroOuro getRegistroOuro() {
        return registroOuro;
    }

    public void setRegistroOuro(RegistroOuro registroOuro) {
        this.registroOuro = registroOuro;
    }

    public RegistroXp getRegistroXp() {
        return registroXp;
    }

    public void setRegistroXp(RegistroXp registroXp) {
        this.registroXp = registroXp;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}