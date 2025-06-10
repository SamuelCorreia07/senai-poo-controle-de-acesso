package com.senai.model.curso;

import java.time.LocalTime;
import java.util.List;

public class Curso {
    private int idCurso;
    private String titulo;
    private List<UC> ucs;
    private int cargaHoraria;
    private String tipo;
    private LocalTime tolerancia;

    public Curso(int idCurso, String titulo, List<UC> ucs, int cargaHoraria, String tipo, LocalTime tolerancia) {
        this.idCurso = idCurso;
        this.titulo = titulo;
        this.ucs = ucs;
        this.cargaHoraria = cargaHoraria;
        this.tipo = tipo;
        this.tolerancia = tolerancia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<UC> getUcs() {
        return ucs;
    }

    public void setUcs(List<UC> ucs) {
        this.ucs = ucs;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalTime getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(LocalTime tolerancia) {
        this.tolerancia = tolerancia;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}