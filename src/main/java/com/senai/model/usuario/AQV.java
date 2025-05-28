package com.senai.model.usuario;

public class AQV extends Usuario {

    public AQV(int id, String nome) {
        super(id, nome);
    }

    @Override
    public String toString() {
        return "AQV: "+
                "ID: " + getId() +
                "Nome: " + getNome();
    }
}