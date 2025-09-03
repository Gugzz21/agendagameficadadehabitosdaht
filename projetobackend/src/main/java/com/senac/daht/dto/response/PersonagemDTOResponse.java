package com.senac.daht.dto.response;

public class PersonagemDTOResponse {
    private int id;
    private Double vida;
    private Double ouro;
    private int xp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
