// PersonagemDTORequest.java
package com.senac.daht.dto.request;
import jakarta.validation.constraints.NotNull;
public class PersonagemDTORequest {
    @NotNull
    private double vida;
    @NotNull
    private double ouro;
    @NotNull
    private int xp;
    @NotNull
    private Integer usuarioId;

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

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}