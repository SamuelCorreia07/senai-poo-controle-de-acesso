package com.senai.model.usuario;

public abstract class Usuario {
    private int nome;
    private int id;

    public Usuario(int nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
