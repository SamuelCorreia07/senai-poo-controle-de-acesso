package com.senai.control.usuario;

import com.senai.model.usuario.Professor;
import com.senai.model.usuario.dao.json.ProfessorDAO;

import java.util.List;
import java.util.Optional;

public class ProfessorController {
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public String cadastrarProfessor(String nome, String login, String senha, String disciplina) {
        Professor novoProfessor = new Professor(0, nome, login, senha, disciplina);
        professorDAO.inserir(novoProfessor);
        return "Professor cadastrado com sucesso!";
    }

    public String atualizarProfessor(int id, String nome, String login, String senha, String disciplina) {
        Optional<Professor> encontrado = professorDAO.buscarPorId(id);
        if (encontrado.isPresent()) {
            Professor atualizado = encontrado.get();
            atualizado.setNome(nome);
            atualizado.setLogin(login);
            atualizado.setSenha(senha);
            atualizado.setDisciplina(disciplina);
            professorDAO.atualizar(atualizado);
            return "Professor atualizado com sucesso!";
        } else {
            return "Professor com ID " + id + " não encontrado.";
        }
    }

    public String deletarProfessor(int id) {
        Optional<Professor> encontrado = professorDAO.buscarPorId(id);
        if (encontrado.isPresent()) {
            professorDAO.deletar(id);
            return "Professor removido com sucesso!";
        } else {
            return "Professor com ID " + id + " não encontrado.";
        }
    }

    public List<Professor> listarProfessores() {
        return professorDAO.listar();
    }
}