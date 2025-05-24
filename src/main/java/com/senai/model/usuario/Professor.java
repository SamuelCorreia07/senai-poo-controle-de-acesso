package com.senai.model.usuario;

public class Professor extends Usuario {
    private String disciplina;

    public Professor(String nome, int id, String disciplina) {
        super(nome, id);
        this.disciplina = disciplina;

    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "disciplina='" + disciplina + '\'' +
                '}';
    }
}

