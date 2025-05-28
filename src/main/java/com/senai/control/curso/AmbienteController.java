package com.senai.control.curso;

import com.senai.model.curso.Ambiente;
import com.senai.model.curso.DAO.json.AmbienteDAO;

import java.util.List;
import java.util.Optional;

public class AmbienteController {
    private final AmbienteDAO ambienteDAO = new AmbienteDAO();

    public String cadastrarAmbiente(String nome, String tipo) {
        ambienteDAO.inserir(new Ambiente(0, nome, tipo));
        return "Ambiente cadastrado.";
    }

    public String atualizarAmbiente(int id, String nome, String tipo) {
        Optional<Ambiente> ambienteOpt = ambienteDAO.buscarPorId(id);
        if (ambienteOpt.isPresent()) {
            ambienteDAO.atualizar(new Ambiente(id, nome, tipo));
            return "Ambiente atualizado.";
        } else {
            return "Ambiente com ID " + id + " não encontrado.";
        }
    }

    public String deletarAmbiente(int id) {
        Optional<Ambiente> ambienteOpt = ambienteDAO.buscarPorId(id);
        if (ambienteOpt.isPresent()) {
            ambienteDAO.deletar(id);
            return "Ambiente removido.";
        } else {
            return "Ambiente com ID " + id + " não encontrado.";
        }
    }

    public List<Ambiente> listarAmbientes() {
        return ambienteDAO.listar();
    }

    public Optional<Ambiente> buscarPorId(int id) {
        return ambienteDAO.buscarPorId(id);
    }

}
