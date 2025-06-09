package com.senai.control.usuario.aluno;

import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.aluno.dao.json.AlunoDAO;
import java.util.List;

public class AlunoController {
    private final AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
    }

    // Cadastrar um aluno
    public String cadastrarAluno(String nome, int idade, String login, String senha, String rfid) {
        // O ID é gerado automaticamente na persistência, por isso passamos 0 para indicar um novo aluno
        Aluno novoAluno = new Aluno(0, nome, login, senha, idade, rfid);
        alunoDAO.inserir(novoAluno);
        return "Alun@ cadastrad@ com sucesso!";
    }

    // Atualizar os dados de um aluno
    public String atualizarAluno(int id, String nome, int idade, String login, String senha, String rfid) {
        Aluno aluno = alunoDAO.buscarPorId(id).orElse(null);
        if (aluno == null) {
            return "Alun@ não encontrad@!";
        }
        aluno.setNome(nome);
        aluno.setIdade(idade);
        aluno.setLogin(login);
        aluno.setSenha(senha);
        aluno.setIdCartaoRfid(rfid);
        alunoDAO.atualizar(aluno);
        return "Alun@ atualizad@ com sucesso!";
    }

    public String removerAluno(int id) {
        Aluno aluno = alunoDAO.buscarPorId(id).orElse(null);
        if (aluno == null) {
            return "Alun@ não encontrad@!";
        }
        alunoDAO.remover(id);
        return "Alun@ removid@ com sucesso!";
    }

    public String buscarPorRfid(String rfid) {
        Aluno aluno = alunoDAO.buscarPorRfid(rfid).orElse(null);
        if (aluno == null) {
            return "\nAlun@ com RFID não encontrad@!";
        }
        return "\n" + aluno.toString();
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.listarTodos();
    }
}