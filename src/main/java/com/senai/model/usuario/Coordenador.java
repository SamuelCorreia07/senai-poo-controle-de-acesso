package com.senai.model.usuario;

public class Coordenador extends Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String departamento;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Coordenador( String nome, String nome1, String cpf, String departamento) {
        super(nome, 0);
        int id1 = 0;
        this.id = id1;
        this.nome = nome1;
        this.cpf = cpf;
        this.departamento = departamento;



    }
}