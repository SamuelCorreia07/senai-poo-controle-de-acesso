package com.senai.control;

import com.senai.model.turma.horario.Horario;
import com.senai.model.turma.horario.DAO.HorarioDAO;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class HorarioController {
    private final HorarioDAO horarioDAO = new HorarioDAO();

    public String cadastrarHorario(int idAluno, int idProfessor, LocalTime hora) {
        Horario horario = new Horario(0, idAluno, idProfessor, hora);
        horarioDAO.inserir(horario);
        return "Horário cadastrado com sucesso!";
    }

    public String atualizarHorario(int id, int idAluno, int idProfessor, LocalTime hora) {
        Optional<Horario> horarioExistente = horarioDAO.buscarPorId(id);

        if (horarioExistente.isPresent()) {
            Horario horario = new Horario(id, idAluno, idProfessor, hora);
            horarioDAO.atualizar(horario);
            return "Horário atualizado com sucesso!";
        } else {
            return "Horário com ID " + id + " não encontrado!";
        }
    }

    public String removerHorario(int id) {
        Optional<Horario> horarioExistente = horarioDAO.buscarPorId(id);

        if (horarioExistente.isPresent()) {
            horarioDAO.remover(id);
            return "Horário removido com sucesso!";
        } else {
            return "Horário com ID " + id + " não encontrado!";
        }
    }

    public List<Horario> listarHorarios() {
        return horarioDAO.listarTodos();
    }
}