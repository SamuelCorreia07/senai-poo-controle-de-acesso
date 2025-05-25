package com.senai.model.usuario;

public abstract class Usuario {
    String nome;
    private int id;

    public Usuario() {
        this.nome = nome;
        this.id = id;
    }


    public String getNome() {
       return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
