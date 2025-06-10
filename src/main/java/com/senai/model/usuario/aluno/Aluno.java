package com.senai.model.usuario.aluno;

import com.senai.model.usuario.Usuario;

import java.time.LocalTime;
import java.util.Objects;

public class Aluno extends Usuario {
    private int idade;
    private String idCartaoRfid;

    public Aluno(int id, String nome, String login, String senha, int idade, String idCartaoRfid) {
        super(id, nome, login, senha);
        this.idade = idade;
        this.idCartaoRfid = idCartaoRfid;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getIdCartaoRfid() {
        return idCartaoRfid;
    }

    public void setIdCartaoRfid(String idCartaoRfid) {
        this.idCartaoRfid = idCartaoRfid;
    }

    public boolean estaAtrasado(LocalTime horarioEntrada, int tolerancia) {
        return LocalTime.now().isAfter(horarioEntrada.plusMinutes(tolerancia));
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Idade: %d | RFID: %s", getId(), getNome(), idade, idCartaoRfid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return getId() == aluno.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }
}