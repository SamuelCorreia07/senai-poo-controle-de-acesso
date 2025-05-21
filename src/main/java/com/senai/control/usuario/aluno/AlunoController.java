package com.senai.control.usuario.aluno;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.aluno.dao.AlunoDAO;
import java.util.List;

public class AlunoController {
    private final AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    // Método para cadastrar aluno
    public String cadastrarAluno(String nome, int idade) {
        Aluno novoAluno = new Aluno(0, nome, idade);  // O ID será gerado no DAO
        alunoDAO.inserir(novoAluno);
        return "Aluno cadastrado com sucesso!";
    }

    // Método para atualizar aluno
    public String atualizarAluno(int id, String nome, int idade) {
        Aluno aluno = alunoDAO.buscarPorId(id).orElse(null);
        if (aluno == null) {
            return "Aluno não encontrado!";
        }
        aluno.setNome(nome);
        aluno.setIdade(idade);
        alunoDAO.atualizar(aluno);
        return "Aluno atualizado com sucesso!";
    }

    // Método para remover aluno
    public String removerAluno(int id) {
        Aluno aluno = alunoDAO.buscarPorId(id).orElse(null);
        if (aluno == null) {
            return "Aluno não encontrado!";
        }
        alunoDAO.remover(id);
        return "Aluno removido com sucesso!";
    }

    // Método para listar alunos
    public List<Aluno> listarAlunos() {
        return alunoDAO.listarTodos();
    }
}