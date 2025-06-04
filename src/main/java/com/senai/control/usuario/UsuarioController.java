package com.senai.control.usuario;

import com.senai.model.usuario.AQV;
import com.senai.model.usuario.Coordenador;
import com.senai.model.usuario.Professor;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.aluno.dao.json.AlunoDAO;
import com.senai.model.usuario.dao.json.AQVDAO;
import com.senai.model.usuario.dao.json.CoordenadorDAO;
import com.senai.model.usuario.dao.json.ProfessorDAO;
import com.senai.util.CriptografiaUtil;

import java.util.List;

public class UsuarioController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
    private final AQVDAO aqvDAO = new AQVDAO();

    public String cadastrarUsuario(String tipo, String nome, String dadoExtra, String login, String senha) {
        switch (tipo) {
            case "1" -> {
                alunoDAO.inserir(new Aluno(0, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
                return "\nAluno cadastrado com sucesso.";
            }
            case "2" -> {
                professorDAO.inserir(new Professor(0, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
                return "\nProfessor cadastrado com sucesso.";
            }
            case "3" -> {
                coordenadorDAO.inserir(new Coordenador(0, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
                return "\nCoordenador cadastrado com sucesso.";
            }
            case "4" -> {
                aqvDAO.inserir(new AQV(0, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
                return "\nAQV cadastrado com sucesso.";
            }
            default -> {
                return "\nTipo inválido.";
            }
        }
    }

    public String atualizarUsuario(String tipo, int id, String nome, String dadoExtra, String login, String senha) {
        switch (tipo) {
            case "1" -> {
                alunoDAO.atualizar(new Aluno(id, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
                return "\nAluno atualizado.";
            }
            case "2" -> {
                professorDAO.atualizar(new Professor(id, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
                return "\nProfessor atualizado.";
            }
            case "3" -> {
                coordenadorDAO.atualizar(new Coordenador(id, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
                return "\nCoordenador atualizado.";
            }
            case "4" -> {
                aqvDAO.atualizar(new AQV(id, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
                return "\nAQV atualizado.";
            }
            default -> {
                return "\nTipo inválido.";
            }
        }
    }

    public String removerUsuario(String tipo, int id) {
        switch (tipo) {
            case "1" -> {
                alunoDAO.remover(id);
                return "\nAluno removido.";
            }
            case "2" -> {
                professorDAO.remover(id);
                return "\nProfessor removido.";
            }
            case "3" -> {
                coordenadorDAO.remover(id);
                return "\nCoordenador removido.";
            }
            case "4" -> {
                aqvDAO.remover(id);
                return "\nAQV removido.";
            }
            default -> {
                return "\nTipo inválido.";
            }
        }
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.listarTodos();
    }

    public List<Professor> listarProfessores() {
        return professorDAO.listarTodos();
    }

    public List<Coordenador> listarCoordenadores() {
        return coordenadorDAO.listarTodos();
    }

    public List<AQV> listarAqvs() {
        return aqvDAO.listarTodos();
    }

    public String atribuirRfid(int id, String rfid) {
        return alunoDAO.buscarPorId(id).map(aluno -> {
            aluno.setIdCartaoRfid(rfid);
            alunoDAO.atualizar(aluno);
            return "\nRFID atribuído com sucesso.";
        }).orElse("\nAluno não encontrado.");
    }
}