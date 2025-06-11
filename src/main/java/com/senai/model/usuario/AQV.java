package com.senai.model.usuario;

public class AQV extends Usuario {

    public AQV(int id, String nome, String login, String senha) {
        super(id, nome, login, senha);
    }

    @Override
    public String getTipo() {
        return "AQV";
    }
}
