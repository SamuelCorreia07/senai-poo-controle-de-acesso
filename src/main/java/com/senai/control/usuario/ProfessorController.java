package com.senai.control.usuario;

import com.senai.model.usuario.Professor;
import com.senai.model.usuario.dao.json.ProfessorDAO;

import java.util.List;

public class ProfessorController {
        private final ProfessorDAO professorDAO = new ProfessorDAO();

        public String cadastrarProfessor(String nome, String disciplina) {
            professorDAO.inserir(new Professor(0, nome, disciplina));
            return "Professor cadastrado!";
        }

        public String atualizarProfessor(String nome, String disciplina) {
            professorDAO.atualizar(new Professor(0, nome, disciplina));
            return "Professor atualizado!";
        }

        public String deletarProfessor(int id) {
            professorDAO.deletar(id);
            return "Professor removido!";
        }

        public List<Professor> listarProfessores() {
            return professorDAO.listar();
        }
    }

