package com.senai.control.usuario.aluno;

import com.senai.model.turma.DAO.json.TurmaDAO;
import com.senai.model.turma.Turma;
import com.senai.model.turma.horario.DAO.HorarioDAO;
import com.senai.model.turma.horario.Horario;
import com.senai.model.usuario.Professor;
import com.senai.model.usuario.aluno.Aluno;
import com.senai.model.usuario.aluno.Ocorrencia;
import com.senai.model.usuario.aluno.dao.json.AlunoDAO;
import com.senai.model.usuario.aluno.dao.json.OcorrenciaDAO;
import com.senai.model.usuario.dao.json.ProfessorDAO;
import com.senai.websocket.WebSocketSender;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class OcorrenciaController {
    private final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final HorarioDAO horarioDAO = new HorarioDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

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

    public String processarEntrada(String rfid) {
        Optional<Aluno> alunoOpt = alunoDAO.buscarPorRfid(rfid);
        if (alunoOpt.isEmpty()) {
            return "[ACESSO NEGADO] Aluno não encontrado para RFID: " + rfid;
        }

        Aluno aluno = alunoOpt.get();

        Optional<Turma> turmaOpt = turmaDAO.buscarPorAluno(aluno);

        if (turmaOpt.isEmpty()) {
            return "[ACESSO] Aluno: " + aluno.getNome() + " - Nenhuma turma atribuída.";
        }

        Optional<Horario> horarioOpt = horarioDAO.buscarHorarioDaTurma(turmaOpt.get().getIdTurma());

        if (horarioOpt.isEmpty()) {
            return "[ACESSO] Aluno: " + aluno.getNome() + " - Nenhum horário atribuído.";
        }

        Horario horario = horarioOpt.get();

        LocalTime horarioEntrada = turmaOpt.get().getHorarioEntrada();
        int tolerancia = turmaOpt.get().getCurso().getTolerancia();

        boolean atrasado = aluno.estaAtrasado(horarioEntrada,tolerancia);

        if (atrasado) {
            Optional<Professor> professorOpt = professorDAO.buscarPorId(horario.getIdProfessor());

            professorOpt.ifPresent(professor -> {
                String msg = "[ATRASO] Aluno " + aluno.getNome() + " chegou atrasado.";
                WebSocketSender.enviarMensagem(msg);
            });
            return "[ATRASO DETECTADO] Aluno: " + aluno.getNome();
        }
        return "[ENTRADA AUTORIZADA] Aluno: " + aluno.getNome();
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
