package com.senac.daht.dto.request;



public class PersonagemDTORequest {
    private Double vida;
    private Double ouro;
    private int xp;
    private Integer usuarioId;


    public double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public double getOuro() {
        return ouro;
    }

    public void setOuro(Double ouro) {
        this.ouro = ouro;
    }

    public double getXp() {
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
