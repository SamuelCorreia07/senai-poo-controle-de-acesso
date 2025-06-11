package com.senai.control;
import com.senai.model.usuario.Usuario;
import com.senai.model.usuario.dao.json.AdministradorDAO;
import com.senai.model.usuario.aluno.dao.json.AlunoDAO;
import com.senai.model.usuario.dao.json.ProfessorDAO;
import com.senai.model.usuario.dao.json.CoordenadorDAO;
import com.senai.model.usuario.dao.json.AQVDAO;

import java.util.Optional;

public class LoginController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();
    private final AQVDAO aqvDAO = new AQVDAO();
    private final AdministradorDAO administradorDAO = new AdministradorDAO();

    public Optional<Usuario> autenticar(String login, String senha) {
        Optional<? extends Usuario> admin = administradorDAO.buscarPorLogin(login);
        if (admin.isPresent() && admin.get().getSenha().equals(senha)) return Optional.of(admin.get());

        Optional<? extends Usuario> prof = professorDAO.buscarPorLogin(login);
        if (prof.isPresent() && prof.get().getSenha().equals(senha)) return Optional.of(prof.get());

        Optional<? extends Usuario> aluno = alunoDAO.buscarPorLogin(login);
        if (aluno.isPresent() && aluno.get().getSenha().equals(senha)) return Optional.of(aluno.get());

        Optional<? extends Usuario> coord = coordenadorDAO.buscarPorLogin(login);
        if (coord.isPresent() && coord.get().getSenha().equals(senha)) return Optional.of(coord.get());

        Optional<? extends Usuario> aqv = aqvDAO.buscarPorLogin(login);
        if (aqv.isPresent() && aqv.get().getSenha().equals(senha)) return Optional.of(aqv.get());

        return Optional.empty();
    }
}