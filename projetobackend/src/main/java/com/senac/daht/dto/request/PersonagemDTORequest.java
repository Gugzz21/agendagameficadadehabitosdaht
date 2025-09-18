
package com.senac.daht.dto.request;
import jakarta.validation.constraints.NotNull;
public class PersonagemDTORequest {
    @NotNull
    private Double vida;
    @NotNull
    private Double ouro;
    @NotNull
    private Double xp;
    @NotNull
    private Integer usuarioId;

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

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}