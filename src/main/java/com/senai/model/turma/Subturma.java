package com.senai.model.turma;

import com.senai.model.turma.horario.Horario;
import com.senai.model.usuario.aluno.Aluno;

import java.util.List;

public class Subturma {
    private int idSubTurma;
    private String nome;
    private List<Aluno> alunos;
    private Horario horario;

    public int getIdSubTurma() {
        return idSubTurma;
    }

    public void setIdSubTurma(int idSubTurma) {
        this.idSubTurma = idSubTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
