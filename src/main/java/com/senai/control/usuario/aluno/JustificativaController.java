package com.senai.control.usuario.aluno;

import com.senai.model.usuario.aluno.Justificativa;
import com.senai.model.usuario.aluno.dao.JustificativaDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class JustificativaController {
    private final JustificativaDAO justificativaDAO = new JustificativaDAO();

    public String cadastrarJustificativa(String tipo, String descricao, LocalDate dataJustificada, int qtdDias) {
        if (tipo.equals("1")) {
            int prazoAceite = 15;
            if (estaDentroDoPrazo(dataJustificada, prazoAceite)) {
                    justificativaDAO.inserir(new Justificativa(0, "Falta", descricao, dataJustificada, qtdDias, prazoAceite));
                    return "Justificativa de Falta cadastrada com sucesso!";
            } else {
                return "A justificativa de Falta está fora do prazo de 15 dias. Justificativa não cadastrada.";
            }
        } else if (tipo.equals("2")) {
            int prazoAceite = 7;
            if (estaDentroDoPrazo(dataJustificada, prazoAceite)) {
                justificativaDAO.inserir(new Justificativa(0, "Ocorrencia", descricao, dataJustificada, qtdDias, prazoAceite));
                return "Justificativa de Ocorrência cadastrada com sucesso!";
            } else {
                return "A justificativa de Falta está fora do prazo de 7 dias. Justificativa não cadastrada.";
            }
        } else {
            return "Tipo inválido. Use 1 para Falta ou 2 para Ocorrência.";
        }
    }

    public String atualizarJustificativa(int id, String tipo, String descricao, LocalDate dataJustificada, int qtdDias) {
        Optional<Justificativa> encontrada = justificativaDAO.buscarPorId(id);

        if (encontrada.isEmpty()) {
            return "Justificativa com ID " + id + " não encontrada.";
        }

        if (tipo.equals("1")) {
            Justificativa atualizada = encontrada.get();
            atualizada.setTipo("Falta");
            atualizada.setDescricao(descricao);
            atualizada.setDataJustificada(dataJustificada);
            atualizada.setQtdDias(qtdDias);
            atualizada.setPrazoAceite(15);
            if (estaDentroDoPrazo(dataJustificada, atualizada.getPrazoAceite())) {
                justificativaDAO.atualizar(atualizada);
                return "Justificativa de Falta atualizada com sucesso!";
            } else {
                return "A justificativa de Falta está fora do prazo de 15 dias. Justificativa não atualizada.";
            }
        } else if (tipo.equals("2")) {
            Justificativa atualizada = encontrada.get();
            atualizada.setTipo("Ocorrência");
            atualizada.setDescricao(descricao);
            atualizada.setDataJustificada(dataJustificada);
            atualizada.setQtdDias(qtdDias);
            atualizada.setPrazoAceite(7);
            if (estaDentroDoPrazo(dataJustificada, atualizada.getPrazoAceite())) {
                justificativaDAO.atualizar(atualizada);
                return "Justificativa de Ocorrência atualizada com sucesso!";
            } else {
                return "A justificativa de Ocorrência está fora do prazo de 7 dias. Justificativa não atualizada.";
            }

        } else {
            return "Tipo inválido. Use 1 para Entrada ou 2 para Saída.";
        }
    }

    public String removerJustificativa(int id) {
        Optional<Justificativa> encontrada = justificativaDAO.buscarPorId(id);

        if (encontrada.isPresent()) {
            justificativaDAO.remover(id);
            return "Justificativa removida com sucesso!";
        } else {
            return "Justificativa com ID " + id + " não encontrada.";
        }
    }

    public List<Justificativa> listarJustificativas() {
        return justificativaDAO.listarTodos();
    }

    private boolean estaDentroDoPrazo(LocalDate dataJustificada, int prazoAceite) {
        return !dataJustificada.plusDays(prazoAceite).isBefore(LocalDate.now());
    }
}
