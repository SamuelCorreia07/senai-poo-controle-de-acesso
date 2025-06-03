package com.senai.model.usuario.aluno;
import com.senai.model.usuario.Usuario;
import java.util.Objects;

public class Aluno extends Usuario {
    private int idade;

    public Aluno(int id, String nome, int idade) {
        super(id, nome);
        this.idade = idade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade > 0) {
            this.idade = idade;
        } else {
            System.out.println("\nIdade inválida!");
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Idade: %d", getId(), getNome(), idade);
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
}