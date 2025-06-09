package com.senai.control.usuario;

import com.senai.model.usuario.AQV;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.Coordenador;
import com.senai.model.usuario.Professor;
import com.senai.model.usuario.dao.json.AQVDAO;
import com.senai.model.usuario.aluno.dao.json.AlunoDAO;
import com.senai.model.usuario.dao.json.CoordenadorDAO;
import com.senai.model.usuario.dao.json.ProfessorDAO;
import com.senai.util.CriptografiaUtil;

import java.util.List;

enum TipoUsuario {
    ALUNO("1", Aluno.class, new AlunoDAO()),
    PROFESSOR("2", Professor.class, new ProfessorDAO()),
    COORDENADOR("3", Coordenador.class, new CoordenadorDAO()),
    AQV("4", AQV.class, new AQVDAO());

    private final String tipo;
    private final Class<?> classe;
    private final Object dao;

    TipoUsuario(String tipo, Class<?> classe, Object dao) {
        this.tipo = tipo;
        this.classe = classe;
        this.dao = dao;
    }

    public String getTipo() {
        return tipo;
    }

    public Class<?> getClasse() {
        return classe;
    }

    public Object getDao() {
        return dao;
    }

    public static TipoUsuario fromString(String tipo) {
        for (TipoUsuario usuario : TipoUsuario.values()) {
            if (usuario.getTipo().equals(tipo)) {
                return usuario;
            }
        }
        throw new IllegalArgumentException("Tipo inválido.");
    }
}

public class UsuarioController {

    public String cadastrarUsuario(String tipo, String nome, String dadoExtra, String login, String senha) {
        TipoUsuario tipoUsuario = TipoUsuario.fromString(tipo);
        Object dao = tipoUsuario.getDao();

        if (dao instanceof AlunoDAO) {
            AlunoDAO alunoDAO = (AlunoDAO) dao;
            alunoDAO.inserir(new Aluno(0, nome, login, senha));
            return "\nAluno cadastrado com sucesso.";
        } else if (dao instanceof ProfessorDAO) {
            ProfessorDAO professorDAO = (ProfessorDAO) dao;
            professorDAO.inserir(new Professor(0, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
            return "\nProfessor cadastrado com sucesso.";
        } else if (dao instanceof CoordenadorDAO) {
            CoordenadorDAO coordenadorDAO = (CoordenadorDAO) dao;
            coordenadorDAO.inserir(new Coordenador(0, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
            return "\nCoordenador cadastrado com sucesso.";
        } else if (dao instanceof AQVDAO) {
            AQVDAO aqvDAO = (AQVDAO) dao;
            aqvDAO.inserir(new AQV(0, nome, login, CriptografiaUtil.hash(senha)));
            return "\nAQV cadastrado com sucesso.";
        }

        return "\nErro no cadastro.";
    }

    public String atualizarUsuario(String tipo, int id, String nome, String dadoExtra, String login, String senha) {
        TipoUsuario tipoUsuario = TipoUsuario.fromString(tipo);
        Object dao = tipoUsuario.getDao();

        if (dao instanceof AlunoDAO) {
            AlunoDAO alunoDAO = (AlunoDAO) dao;
            alunoDAO.atualizar(new Aluno(id, nome, login, senha));
            return "\nAluno atualizado.";
        } else if (dao instanceof ProfessorDAO) {
            ProfessorDAO professorDAO = (ProfessorDAO) dao;
            professorDAO.atualizar(new Professor(id, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
            return "\nProfessor atualizado.";
        } else if (dao instanceof CoordenadorDAO) {
            CoordenadorDAO coordenadorDAO = (CoordenadorDAO) dao;
            coordenadorDAO.atualizar(new Coordenador(id, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
            return "\nCoordenador atualizado.";
        } else if (dao instanceof AQVDAO) {
            AQVDAO aqvDAO = (AQVDAO) dao;
            aqvDAO.atualizar(new AQV(id, nome, dadoExtra, login, CriptografiaUtil.hash(senha)));
            return "\nAQV atualizado.";
        }

        return "\nErro na atualização.";
    }

    public String removerUsuario(String tipo, int id) {
        TipoUsuario tipoUsuario = TipoUsuario.fromString(tipo);
        Object dao = tipoUsuario.getDao();

        if (dao instanceof AlunoDAO) {
            AlunoDAO alunoDAO = (AlunoDAO) dao;
            alunoDAO.remover(id);
            return "\nAluno removido.";
        } else if (dao instanceof ProfessorDAO) {
            ProfessorDAO professorDAO = (ProfessorDAO) dao;
            professorDAO.deletar(id);
            return "\nProfessor removido.";
        } else if (dao instanceof CoordenadorDAO) {
            CoordenadorDAO coordenadorDAO = (CoordenadorDAO) dao;
            coordenadorDAO.remover(id);
            return "\nCoordenador removido.";
        } else if (dao instanceof AQVDAO) {
            AQVDAO aqvDAO = (AQVDAO) dao;
            aqvDAO.remover(id);
            return "\nAQV removido.";
        }

        return "\nErro na remoção.";
    }

    public List<Aluno> listarAlunos() {
        return new AlunoDAO().listarTodos();
    }

    public List<Professor> listarProfessores() {
        return new ProfessorDAO().listar();
    }

    public List<Coordenador> listarCoordenadores() {
        return new CoordenadorDAO().listar();
    }

    public List<AQV> listarAqvs() {
        return new AQVDAO().listarTodos();
    }

    public String atribuirRfid(int id, String rfid) {
        return new AlunoDAO().buscarPorId(id).map(aluno -> {
            aluno.setIdCartaoRfid(rfid);
            new AlunoDAO().atualizar(aluno);
            return "\nRFID atribuído com sucesso.";
        }).orElse("\nAluno não encontrado.");
    }
}