package com.senai.model.usuario;

public class Coordenador extends Usuario {
    private int id;
    private String nome;
    private String email;
    private String setorResponsavel;

    public Coordenador(int nome, int id, int id1, String nome1, String email, String setorResponsavel) {
        super(nome, id);
        this.id = id1;
        this.nome = nome1;
        this.email = email;
        this.setorResponsavel = setorResponsavel;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
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

    @Override
    public String toString() {
        return "Coordenador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", setorResponsavel='" + setorResponsavel + '\'' +
                '}';

    }
}