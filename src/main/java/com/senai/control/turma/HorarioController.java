package com.senai.control.turma;

import com.senai.model.turma.horario.Horario;
import com.senai.model.turma.horario.DAO.HorarioDAO;
import java.util.List;
import java.util.Optional;

public class HorarioController {
    private final HorarioDAO horarioDAO = new HorarioDAO();

    public String cadastrarHorario(int idTurma, int idProfessor) {
        Horario horario = new Horario(0, idTurma, idProfessor);
        horarioDAO.inserir(horario);
        return "Horário cadastrado com sucesso!";
    }

    public String atualizarHorario(int id, int idTurma, int idProfessor) {
        Optional<Horario> horarioExistente = horarioDAO.buscarPorId(id);

        if (horarioExistente.isPresent()) {
            Horario atualizado = horarioExistente.get();
            atualizado.setIdTurma(idTurma);
            atualizado.setIdProfessor(idProfessor);
            horarioDAO.atualizar(atualizado);
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