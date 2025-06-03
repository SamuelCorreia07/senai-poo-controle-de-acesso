package com.senai.control.usuario;

import com.senai.model.usuario.Professor;
import com.senai.model.usuario.dao.json.ProfessorDAO;

import java.util.List;
import java.util.Optional;

public class ProfessorController {
        private final ProfessorDAO professorDAO = new ProfessorDAO();

        public String cadastrarProfessor(String nome, String disciplina) {
            professorDAO.inserir(new Professor(0, nome, disciplina));
            return "Professor cadastrado!";
        }

        public String atualizarProfessor(int id, String nome, String disciplina) {
            Optional<Professor> encontrado = professorDAO.buscarPorId(id);
            if (encontrado.isPresent()) {
                Professor atualizado = encontrado.get();
                atualizado.setNome(nome);
                atualizado.setDisciplina(disciplina);
                professorDAO.atualizar(atualizado);
                return "Professor atualizado!";
            } else return "Professor com ID " + id + " não encontrado.";
        }

        public String deletarProfessor(int id) {
            Optional<Professor> encontrado = professorDAO.buscarPorId(id);
            if (encontrado.isPresent()) {
                professorDAO.deletar(id);
                return "Professor removido!";
            } else return "Professor com ID " + id + " não encontrado.";
        }

        public List<Professor> listarProfessores() {
            return professorDAO.listar();
        }
    }

