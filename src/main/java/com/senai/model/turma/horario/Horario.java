package com.senai.model.turma.horario;

public class Horario {
    private int id;
    private int idTurma;
    private int idProfessor;

    public Horario(int id, int idTurma, int idProfessor) {
        this.id = id;
        this.idTurma = idTurma;
        this.idProfessor = idProfessor;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
}
