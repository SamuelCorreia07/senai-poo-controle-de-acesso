package com.senai.model.curso;

import com.senai.model.usuario.Professor;

import java.util.ArrayList;
import java.util.List;

public class UC {
    private int id;
    private String nome;
    private List<Professor> professores;
    private int cargaHoraria;

    public UC(int id, String nome, int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.professores = new ArrayList<>();
        this.cargaHoraria = cargaHoraria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
