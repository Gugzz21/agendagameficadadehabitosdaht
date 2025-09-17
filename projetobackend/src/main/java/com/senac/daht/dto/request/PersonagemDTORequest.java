
package com.senac.daht.dto.request;
import jakarta.validation.constraints.NotNull;
public class PersonagemDTORequest {
    @NotNull
    private int vida;
    @NotNull
    private int ouro;
    @NotNull
    private int xp;
    @NotNull
    private Integer usuarioId;

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

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}