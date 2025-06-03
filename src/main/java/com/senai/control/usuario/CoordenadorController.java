package com.senai.control.usuario;

import com.senai.model.usuario.Coordenador;
import com.senai.model.usuario.dao.json.CoordenadorDAO;

import java.util.List;
import java.util.Optional;

public class CoordenadorController {
    private final CoordenadorDAO coordenadorDAO = new CoordenadorDAO();

    public String inserirCoordenador(String nome) {
        coordenadorDAO.inserir(new Coordenador(0, nome));
        return "Coordenador cadastrado com sucesso!";
    }

    public List<Coordenador> listarCoordenadores() {
        return coordenadorDAO.listar();
    }

    public String atualizarCoordenador(int id, String nome) {
        Optional<Coordenador> encontrado = coordenadorDAO.buscarPorId(id);
        if (encontrado.isPresent()) {
            Coordenador atualizado = encontrado.get();
            atualizado.setNome(nome);
            coordenadorDAO.atualizar(atualizado);
            return "Coordenador atualizado com sucesso!";
        } else return "Coordenador com ID " + id + " não encontrado.";
    }

    public String removerCoordenador(int id) {
        Optional<Coordenador> encontrado = coordenadorDAO.buscarPorId(id);
        if (encontrado.isPresent()) {
            coordenadorDAO.remover(id);
            return "Coordenador removido com sucesso!";
        } else return "Coordenador com ID " + id + " não encontrado.";
    }
}
