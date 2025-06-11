package com.senai.model.usuario;

public class Professor extends Usuario {
    private String disciplina;

    public Professor(int id, String nome, String login, String senha, String disciplina) {
        super(id, nome, login, senha);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String getTipo() {
        return "Professor";
    }

    @Override
    public String toString() {
        return super.toString() + ", Disciplina: " + disciplina;
    }
}
