package com.senai.control.turma;



import com.senai.model.turma.Subturma;
import com.senai.model.turma.Turma;
import com.senai.model.turma.TurmaDAO;

import java.util.ArrayList;
import java.util.List;

public class TurmaController {
    private final TurmaDAO turmaDAO = new TurmaDAO();

    public String cadastrarTurma(String nome, String curso, String dataInicio, int qtdSemestre, String horarioEntrada) {
        turmaDAO.inserir(new Turma(0, nome, curso, dataInicio, qtdSemestre, horarioEntrada, new ArrayList<>()));
        return "Turma cadastrada.";
    }

    public String atualizarTurma(int idTurma, String nome, String curso, String dataInicio, int qtdSemestre, String horarioEntrada) {
        turmaDAO.atualizar(new Turma(idTurma, nome, curso, dataInicio, qtdSemestre, horarioEntrada, new ArrayList<>()));
        return "Turma atualizada.";
    }

    public String removerTurma(int idTurma) {
        turmaDAO.deletar(idTurma);
        return "Turma removida.";
    }

    public List<Turma> listarTurmas(){
        return turmaDAO.listar();
    }
}
