package com.senai.control;
import com.senai.model.turma.HorarioDAO;
import com.senai.model.turma.horario.Horario;
import java.time.LocalTime;
import java.util.List;

public class HorarioController {
    private final HorarioDAO horarioDAO = new HorarioDAO();

    public String cadastrarHorario(int idAluno, int idProfessor, LocalTime hora) {
        horarioDAO.inserir(new Horario(0, idAluno, idProfessor, hora));
        return "Horário cadastrado.";
    }

    public String atualizarHorario(int id, int idAluno, int idProfessor, LocalTime hora) {
        horarioDAO.atualizar(new Horario(id, idAluno, idProfessor, hora));
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
