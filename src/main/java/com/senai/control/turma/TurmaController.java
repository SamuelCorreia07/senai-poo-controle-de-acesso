package com.senai.control.turma;

import com.senai.model.curso.Curso;
import com.senai.model.curso.UC;
import com.senai.model.turma.Turma;
import com.senai.model.turma.DAO.json.TurmaDAO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmaController {
    private final TurmaDAO turmaDAO = new TurmaDAO();

    public String cadastrarTurma(String nome, Curso curso, String dataInicio, int qtdSemestre, LocalTime horarioEntrada) {
        // Criando uma nova turma
        Turma novaTurma = new Turma(0, nome, curso, dataInicio, qtdSemestre, horarioEntrada, new ArrayList<>());

        turmaDAO.inserir(novaTurma);
        return "Turma cadastrada com sucesso!";
    }

    public String atualizarTurma(int idTurma, String nome, Curso curso, String dataInicio, int qtdSemestre, LocalTime horarioEntrada) {
        Optional<Turma> turmaOptional = turmaDAO.buscarPorId(idTurma);

        if (turmaOptional.isPresent()) {
            Turma turmaAtualizada = turmaOptional.get();

            turmaAtualizada.setNome(nome);
            turmaAtualizada.setCurso(curso);
            turmaAtualizada.setDataInicio(dataInicio);
            turmaAtualizada.setQtdSemestre(qtdSemestre);
            turmaAtualizada.setHorarioEntrada(horarioEntrada);

            turmaDAO.atualizar(turmaAtualizada);
            return "Turma atualizada com sucesso!";
        } else {
            return "Turma com ID " + idTurma + " não encontrada.";
        }
    }

    public String removerTurma(int idTurma) {
        Optional<Turma> turmaOptional = turmaDAO.buscarPorId(idTurma);

        if (turmaOptional.isPresent()) {
            turmaDAO.deletar(idTurma);
            return "Turma removida com sucesso!";
        } else {
            return "Turma com ID " + idTurma + " não encontrada.";
        }
    }

    public List<Turma> listarTurmas() {
        return turmaDAO.listar();
    }

    // Método para buscar um curso por ID
    public Curso buscarCursoPorId(int idCurso) {
        List<UC> ucs = new ArrayList<>();

        LocalTime tolerancia = LocalTime.of(0, 20);

        return new Curso(idCurso, "Desenvolvimento de Sistemas", ucs, 100, "TEC", tolerancia);
    }
}