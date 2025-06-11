package com.senai.model.turma.horario;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Horario {
    private int id;
    private int idTurma;
    private int idProfessor;
    private LocalTime horaInicio;

    public Horario(int id, int idTurma, int idProfessor, LocalTime horaInicio) {
        this.id = id;
        this.idTurma = idTurma;
        this.idProfessor = idProfessor;
        this.horaInicio = horaInicio;
    }

    public Horario(int idTurma, int idProfessor, LocalTime horaInicio) {
        this.idTurma = idTurma;
        this.idProfessor = idProfessor;
        this.horaInicio = horaInicio;
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

    public LocalTime getHoraInicio() {
        return horaInicio;
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

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraInicioFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return horaInicio.format(formatter);
    }

    public boolean validarHorario() {
        return horaInicio != null && idTurma > 0 && idProfessor > 0;
    }
}
