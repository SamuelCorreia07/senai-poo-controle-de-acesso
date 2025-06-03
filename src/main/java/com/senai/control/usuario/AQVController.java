package com.senai.control.usuario;

import com.senai.model.usuario.AQV;
import com.senai.model.usuario.dao.json.AQVDAO;

import java.util.List;
import java.util.Optional;

public class AQVController {
    private final AQVDAO aqvDAO = new AQVDAO();

    public String inserirAQV(String nome) {
        aqvDAO.inserir(new AQV(0, nome));
        return "AQV cadastrado com sucesso!";
    }

    public List<AQV> listarAQVs() {
        return aqvDAO.listarTodos();
    }

    public String atualizarAQV(int id, String nome) {
        Optional<AQV> encontrado = aqvDAO.buscarPorId(id);
        if (encontrado.isPresent()) {
            AQV atualizado = encontrado.get();
            atualizado.setNome(nome);
            aqvDAO.atualizar(atualizado);
            return "AQV atualizado com sucesso!";
        } else return "AQV com ID " + id + " não encontrado.";
    }

    public String removerAQV(int id) {
        Optional<AQV> encontrado = aqvDAO.buscarPorId(id);
        if (encontrado.isPresent()) {
            aqvDAO.remover(id);
            return "AQV removido com sucesso!";
        } else return "AQV com ID " + id + " não encontrado.";
    }
}