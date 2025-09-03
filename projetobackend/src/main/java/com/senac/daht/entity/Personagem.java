package com.senac.daht.entity;

import com.senac.daht.entity.Missao;
import com.senac.daht.entity.RegistroOuro;
import com.senac.daht.entity.RegistroXp;
import com.senac.daht.entity.Usuario;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "personagem")
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personagem_id")
    private int id;

    @Column(name = "personagem_vida")
    private Double vida;

    @Column(name = "personagem_ouro")
    private Double ouro;

    @Column(name = "personagem_xp")
    private int xp;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Set<Missao> missoes;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Set<RegistroOuro> registroouros;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    private Set<RegistroXp> registroxps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Missao> getMissoes() {
        return missoes;
    }

    public void setMissoes(Set<Missao> missoes) {
        this.missoes = missoes;
    }

    public Set<RegistroOuro> getRegistroouros() {
        return registroouros;
    }

    public void setRegistroouros(Set<RegistroOuro> registroouros) {
        this.registroouros = registroouros;
    }

    public Set<RegistroXp> getRegistroxps() {
        return registroxps;
    }

    public void setRegistroxps(Set<RegistroXp> registroxps) {
        this.registroxps = registroxps;
    }
}