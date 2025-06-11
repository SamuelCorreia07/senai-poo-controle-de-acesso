package com.senai.control.turma;

import com.senai.model.turma.horario.DAO.HorarioDAO;
import com.senai.model.turma.horario.Horario;
import java.time.LocalTime;
import java.util.List;

public class HorarioController {
    private final HorarioDAO horarioDAO = new HorarioDAO();

    public String cadastrarHorario(int idProfessor, int idAluno, LocalTime horaInicio) {
        horarioDAO.inserir(new Horario(0, idAluno, idProfessor, horaInicio));
        return "Horário cadastrado.";
    }

    public String atualizarHorario(int id, int idAluno, int idProfessor, LocalTime horaInicio) {
        horarioDAO.atualizar(new Horario(id, idAluno, idProfessor, horaInicio));
        return "Horário atualizado.";
    }

    public String removerHorario(int id) {
        horarioDAO.remover(id);
        return "Horário removido.";
    }

    public List<Horario> listarHorarios() {
        return horarioDAO.listarTodos();
    }
}