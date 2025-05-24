package com.senai.control.usuario.aluno;

import com.senai.model.usuario.aluno.Ocorrencia;
import com.senai.model.usuario.aluno.dao.OcorrenciaDAO;

import java.util.List;
import java.util.Optional;

public class OcorrenciaController {
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

    public String cadastrarOcorrencia(String tipo, String descricao) {
        if (tipo.equals("1")) {
            ocorrenciaDAO.inserir(new Ocorrencia(0, "Entrada", descricao));
            return "Occorência de entrada cadastrada com sucesso!";
        } else if (tipo.equals("2")) {
            ocorrenciaDAO.inserir(new Ocorrencia(0, "Saida", descricao));
            return "Ocorrência de saida cadastrada com sucesso!";
        } else {
            return "Tipo inválido";
        }
    }

    public String atualizarOcorrencia(int id, String tipo, String descricao) {
        if (tipo.equals("1")) {
            ocorrenciaDAO.atualizar(new Ocorrencia(id, "Entrada", descricao));
            return "Occorência de entrada cadastrada com sucesso!";
        } else if (tipo.equals("2")) {
            ocorrenciaDAO.atualizar(new Ocorrencia(id, "Saida", descricao));
            return "Ocorrência de saida cadastrada com sucesso!";
        } else {
            return "Tipo inválido";
        }
    }

    public String removerOcorrencia(int id) {
        ocorrenciaDAO.remover(id);
        return "Ocorrência removida";
    }

    public List<Ocorrencia> listarOcorrencias() {return ocorrenciaDAO.listarTodos();}

    public Optional<Ocorrencia> buscarPorId(int id) {return ocorrenciaDAO.buscarPorId(id);}
}
