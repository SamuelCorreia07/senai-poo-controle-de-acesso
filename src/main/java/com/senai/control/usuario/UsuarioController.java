package com.senai.control.usuario;

import com.senai.model.usuario.AQV;
import com.senai.model.usuario.Administrador;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.Coordenador;
import com.senai.model.usuario.Professor;
import com.senai.model.usuario.dao.json.AdministradorDAO;
import com.senai.model.usuario.dao.json.AQVDAO;
import com.senai.model.usuario.aluno.dao.json.AlunoDAO;
import com.senai.model.usuario.dao.json.CoordenadorDAO;
import com.senai.model.usuario.dao.json.ProfessorDAO;
import com.senai.util.CriptografiaUtil;

import java.util.List;

enum TipoUsuario {
    ALUNO("Aluno", Aluno.class, new AlunoDAO()),
    PROFESSOR("Professor", Professor.class, new ProfessorDAO()),
    COORDENADOR("Coordenador", Coordenador.class, new CoordenadorDAO()),
    AQV("AQV", AQV.class, new AQVDAO()),
    ADMINISTRADOR("Administrador",Administrador.class, new AdministradorDAO());

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

    public String cadastrarUsuario(String tipo, String nome, String login, String senha, Object... dados) {
        TipoUsuario tipoUsuario = TipoUsuario.fromString(tipo);
        Object dao = tipoUsuario.getDao();

        if (dao instanceof AlunoDAO alunoDAO) {
            int idade = (int) dados[0];
            String rfid = (String) dados[1];
            alunoDAO.inserir(new Aluno(0, nome, login, CriptografiaUtil.hash(senha), idade, rfid));
            return "\nAluno cadastrado com sucesso.";
        } else if (dao instanceof ProfessorDAO professorDAO) {
            String disciplina = (String) dados[0];
            professorDAO.inserir(new Professor(0, nome, login, CriptografiaUtil.hash(senha), disciplina));
            return "\nProfessor cadastrado com sucesso.";
        } else if (dao instanceof CoordenadorDAO coordenadorDAO) {
            String departamento = (String) dados[0];
            coordenadorDAO.inserir(new Coordenador(0, nome, login, CriptografiaUtil.hash(senha), departamento));
            return "\nCoordenador cadastrado com sucesso.";
        } else if (dao instanceof AQVDAO aqvDAO) {
            aqvDAO.inserir(new AQV(0, nome, login, CriptografiaUtil.hash(senha)));
            return "\nAQV cadastrado com sucesso.";
        }

        return "\nErro no cadastro.";
    }

    public String atualizarUsuario(String tipo, int id, String nome, String login, String senha, Object... dados) {
        TipoUsuario tipoUsuario = TipoUsuario.fromString(tipo);
        Object dao = tipoUsuario.getDao();

        if (dao instanceof AlunoDAO alunoDAO) {
            int idade = (int) dados[0];
            String rfid = (String) dados[1];
            alunoDAO.atualizar(new Aluno(id, nome, login, senha, idade, rfid));
            return "\nAluno atualizado com sucesso.";
        } else if (dao instanceof ProfessorDAO professorDAO) {
            String disciplina = (String) dados[0];
            professorDAO.atualizar(new Professor(id, nome, login, senha, disciplina));
            return "\nProfessor atualizado com sucesso.";
        } else if (dao instanceof CoordenadorDAO coordenadorDAO) {
            String departamento = (String) dados[0];
            coordenadorDAO.atualizar(new Coordenador(id, nome, login, senha, departamento));
            return "\nCoordenador atualizado com sucesso.";
        } else if (dao instanceof AQVDAO aqvDAO) {
            aqvDAO.atualizar(new AQV(id, nome, login, senha));
            return "\nAQV atualizado com sucesso.";
        }

        return "\nErro na atualização.";
    }

    public String removerUsuario(String tipo, int id) {
        TipoUsuario tipoUsuario = TipoUsuario.fromString(tipo);
        Object dao = tipoUsuario.getDao();

        if (dao instanceof AlunoDAO alunoDAO) {
            alunoDAO.remover(id);
            return "\nAluno removido.";
        } else if (dao instanceof ProfessorDAO professorDAO) {
            professorDAO.deletar(id);
            return "\nProfessor removido.";
        } else if (dao instanceof CoordenadorDAO coordenadorDAO) {
            coordenadorDAO.remover(id);
            return "\nCoordenador removido.";
        } else if (dao instanceof AQVDAO aqvDAO) {
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