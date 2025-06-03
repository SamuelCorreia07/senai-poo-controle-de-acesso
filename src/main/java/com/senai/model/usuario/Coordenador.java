package com.senai.model.usuario;

public class Coordenador extends Usuario {

    public Coordenador(int id, String nome) {
        super(id, nome);
    }

    @Override
    public String toString() {
        return "Coordenador: " +
                "ID: " + getId() +
                "Nome: " + getNome();
    }
}