package com.senai.model.usuario;

public class Coordenador extends Usuario {
    private String cpf;
    private String departamento;

    public Coordenador(int i, String cpf, String departamento, String dept) {
        this.cpf = cpf;
        this.departamento = departamento;
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

    @Override
    public String toString() {
        return "Coordenador{" +
                "cpf='" + cpf + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}