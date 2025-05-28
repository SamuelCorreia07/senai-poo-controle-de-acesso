package com.senai.control.usuario.aluno;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.aluno.dao.json.AlunoDAO;
import java.util.List;

public class AlunoController {
    private final AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    public String cadastrarAluno(String nome, int idade) {
        Aluno novoAluno = new Aluno(0, nome, idade);
        alunoDAO.inserir(novoAluno);
        return "\nAlun@ cadastrad@ com sucesso!";
    }

    public String atualizarAluno(int id, String nome, int idade) {
        Aluno aluno = alunoDAO.buscarPorId(id).orElse(null);
        if (aluno == null) {
            return "\nAlun@ não encontrad@!";
        }
        aluno.setNome(nome);
        aluno.setIdade(idade);
        alunoDAO.atualizar(aluno);
        return "\nAlun@ atualizad@ com sucesso!";
    }

    public String removerAluno(int id) {
        Aluno aluno = alunoDAO.buscarPorId(id).orElse(null);
        if (aluno == null) {
            return "\nAlun@ não encontrad@!";
        }
        alunoDAO.remover(id);
        return "\nAlun@ removid@ com sucesso!";
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.listarTodos();
    }
}