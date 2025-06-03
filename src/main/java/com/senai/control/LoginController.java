package com.senai.control;
import com.senai.model.usuario.Usuario;
import com.senai.model.usuario.dao.AdministradorDAO;
import com.senai.model.usuario.aluno.dao.AlunoDAO;
import com.senai.model.usuario.dao.ProfessorDAO;

import java.util.Optional;

public class LoginController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final AdministradorDAO administradorDAO = new AdministradorDAO();

    public Optional<Usuario> autenticar(String login, String senha) {
        Optional<? extends Usuario> admin = administradorDAO.buscarPorLogin(login);
        if (admin.isPresent() && admin.get().getSenha().equals(senha)) return Optional.of(admin.get());

        Optional<? extends Usuario> prof = professorDAO.buscarPorLogin(login);
        if (prof.isPresent() && prof.get().getSenha().equals(senha)) return Optional.of(prof.get());

        Optional<? extends Usuario> aluno = alunoDAO.buscarPorLogin(login);
        if (aluno.isPresent() && aluno.get().getSenha().equals(senha)) return Optional.of(aluno.get());

        return Optional.empty();
    }
}