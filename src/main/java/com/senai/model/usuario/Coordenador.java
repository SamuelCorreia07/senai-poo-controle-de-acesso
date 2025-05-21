package com.senai.model.usuario;

public class Coordenador extends Usuario {
    private int id;
    private String nome;
    private String email;
    private String setorResponsavel;

    public Coordenador(int id, String nome, String email, String setorResponsavel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.setorResponsavel = setorResponsavel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }
}
