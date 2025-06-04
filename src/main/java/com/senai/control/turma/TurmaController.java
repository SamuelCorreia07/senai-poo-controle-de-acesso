package com.senai.control.turma;



import com.senai.model.curso.Curso;
import com.senai.model.turma.Turma;
import com.senai.model.turma.DAO.json.TurmaDAO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmaController {
    private final TurmaDAO turmaDAO = new TurmaDAO();

    public String cadastrarTurma(String nome, Curso curso, String dataInicio, int qtdSemestre, LocalTime horarioEntrada) {
        turmaDAO.inserir(new Turma(0, nome, curso, dataInicio, qtdSemestre, horarioEntrada, new ArrayList<>()));
        return "Turma cadastrada.";
    }

    public String atualizarTurma(int idTurma, String nome, Curso curso, String dataInicio, int qtdSemestre, LocalTime horarioEntrada) {
        Optional<Turma> encontrada = turmaDAO.buscarPorId(idTurma);
        if (encontrada.isPresent()) {
            Turma atualizada = encontrada.get();
            atualizada.setNome(nome);
            atualizada.setCurso(curso);
            atualizada.setDataInicio(dataInicio);
            atualizada.setQtdSemestre(qtdSemestre);
            atualizada.setHorarioEntrada(horarioEntrada);
            turmaDAO.atualizar(atualizada);
            return "Turma atualizada.";
        } else return "Turma com ID " + idTurma + " não encontrada.";
    }

    public String removerTurma(int idTurma) {
        Optional<Turma> encontrada = turmaDAO.buscarPorId(idTurma);
        if (encontrada.isPresent()) {
            turmaDAO.deletar(idTurma);
            return "Turma removida.";
        } else return "Turma com ID " + idTurma + " não encontrada.";
    }

    public List<Turma> listarTurmas(){
        return turmaDAO.listar();
    }
}
