package com.senai.model.turma;

import com.senai.model.curso.Curso;

import java.time.LocalTime;
import java.util.List;

public class Turma {
    private int idTurma;
    private String nome;
    private Curso curso;
    private List<Subturma> subturmas;
    private String dataInicio;
    private int qtdSemestre;
    private LocalTime horarioEntrada;

    public Turma(int idTurma, String nome, Curso curso, String dataInicio, int qtdSemestre, LocalTime horarioEntrada, List<Subturma> subturmas) {
        this.idTurma = idTurma;
        this.nome = nome;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.qtdSemestre = qtdSemestre;
        this.horarioEntrada = horarioEntrada;
        this.subturmas = subturmas;
    }

    public Turma() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Subturma> getSubturmas() {
        return subturmas;
    }

    public void setSubturmas(List<Subturma> subturmas) {
        this.subturmas = subturmas;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getQtdSemestre() {
        return qtdSemestre;
    }

    public void setQtdSemestre(int qtdSemestre) {
        this.qtdSemestre = qtdSemestre;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }
}
