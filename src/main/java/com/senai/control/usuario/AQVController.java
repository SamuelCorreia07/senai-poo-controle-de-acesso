package com.senai.control.usuario;

import com.senai.model.usuario.AQV;
import com.senai.model.usuario.dao.json.AQVDAO;

import java.util.List;
import java.util.Optional;

public class AQVController {
    private final AQVDAO aqvDAO = new AQVDAO();

    public String inserirAQV(String nome, String login, String senha) {
        AQV novoAQV = new AQV(0, nome, login, senha);
        aqvDAO.inserir(novoAQV);
        return "AQV cadastrado com sucesso!";
    }

    public List<AQV> listarAQVs() {
        return aqvDAO.listarTodos();
    }

    public String atualizarAQV(int id, String nome, String login, String senha) {
        Optional<AQV> encontrado = aqvDAO.buscarPorId(id);
        if (encontrado.isPresent()) {
            AQV atualizado = encontrado.get();
            atualizado.setNome(nome);
            atualizado.setLogin(login);
            atualizado.setSenha(senha);
            aqvDAO.atualizar(atualizado);
            return "AQV atualizado com sucesso!";
        } else {
            return "AQV com ID " + id + " não encontrado.";
        }
    }

    public String removerAQV(int id) {
        Optional<AQV> encontrado = aqvDAO.buscarPorId(id);
        if (encontrado.isPresent()) {
            aqvDAO.remover(id); // Remove o AQV pelo ID
            return "AQV removido com sucesso!";
        } else {
            return "AQV com ID " + id + " não encontrado.";
        }
    }

    public AQV buscarPorId(int id) {
        return aqvDAO.buscarPorId(id).orElse(null);
    }
}