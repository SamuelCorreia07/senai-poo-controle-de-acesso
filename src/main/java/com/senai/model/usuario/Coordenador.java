package com.senai.model.usuario;

public class Coordenador extends Usuario {
    private String departamento;

    public Coordenador(int id, String nome, String departamento) {
        super(id, nome);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Coordenador{" +
                "departamento='" + departamento + '\'' +
                '}';
    }
}