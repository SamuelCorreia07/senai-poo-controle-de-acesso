package com.senai.model.turma.horario;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Horario {
    private int id;
    private int idAluno;
    private int idProfessor;
    private LocalTime horaInicio;

    public Horario(int id, int idAluno, int idProfessor, LocalTime horaInicio) {
        this.id = id;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
        this.horaInicio = horaInicio;
    }

    public Horario(int idAluno, int idProfessor, LocalTime horaInicio) {
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
        this.horaInicio = horaInicio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraInicioFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return horaInicio.format(formatter);
    }

    public boolean validarHorario() {
        return horaInicio != null && idAluno > 0 && idProfessor > 0;
    }
}
