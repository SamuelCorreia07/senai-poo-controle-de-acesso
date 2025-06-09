package com.senai.model.usuario;

public class Coordenador extends Usuario {
    private String nome;
    private String departamento;

    public Coordenador(int id, String nome, String login, String senha, String departamento) {
        super(id, nome, login, senha);
        this.departamento = departamento;
    }

    @Override
    public String getTipo() {
        return "Coordenador";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Departamento: %s", getId(), nome, departamento);
    }
}