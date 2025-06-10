package com.senai.control.usuario.aluno;

import com.senai.model.turma.DAO.json.TurmaDAO;
import com.senai.model.usuario.aluno.Ocorrencia;
import com.senai.model.usuario.aluno.dao.json.OcorrenciaDAO;

import java.util.List;
import java.util.Optional;

public class OcorrenciaController {
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
    private final TurmaDAO turmaDAO = new TurmaDAO();

    public String cadastrarOcorrencia(String tipo, String descricao) {
        if (tipo.equals("1")) {
            ocorrenciaDAO.inserir(new Ocorrencia(0, "Entrada", descricao));
            return "Occorência de entrada cadastrada com sucesso!";
        } else if (tipo.equals("2")) {
            ocorrenciaDAO.inserir(new Ocorrencia(0, "Saida", descricao));
            return "Ocorrência de saida cadastrada com sucesso!";
        } else {
            return "Tipo inválido. Use 1 para Entrada ou 2 para Saída.";
        }
    }

    public String atualizarOcorrencia(int id, String tipo, String descricao) {
        Optional<Ocorrencia> encontrada = ocorrenciaDAO.buscarPorId(id);

        if (encontrada.isEmpty()) {
            return "Ocorrência com ID " + id + " não encontrada.";
        }

        if (tipo.equals("1")) {
            Ocorrencia atualizada = encontrada.get();
            atualizada.setTipo("Entrada");
            atualizada.setDescricao(descricao);
            ocorrenciaDAO.atualizar(atualizada);
            return "Ocorrência de entrada atualizada com sucesso!";
        } else if (tipo.equals("2")) {
            Ocorrencia atualizada = encontrada.get();
            atualizada.setTipo("Saída");
            atualizada.setDescricao(descricao);
            ocorrenciaDAO.atualizar(atualizada);
            return "Ocorrência de saída atualizada com sucesso!";
        } else {
            return "Tipo inválido. Use 1 para Entrada ou 2 para Saída.";
        }
    }

    public String removerOcorrencia(int id) {
        Optional<Ocorrencia> encontrada = ocorrenciaDAO.buscarPorId(id);

        if (encontrada.isPresent()) {
            ocorrenciaDAO.remover(id);
            return "Ocorrência removida com sucesso!";
        } else {
            return "Ocorrência com ID " + id + " não encontrada.";
        }
    }

    public List<Ocorrencia> listarOcorrencias() {return ocorrenciaDAO.listarTodos();}
}
