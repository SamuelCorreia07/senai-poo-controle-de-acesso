package com.senai.control.curso;

import com.senai.model.curso.Curso;
import com.senai.model.curso.DAO.json.CursoDAO;
import com.senai.model.curso.UC;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoController {
    private final CursoDAO cursoDAO = new CursoDAO();

    public String cadastrarCurso(String titulo, int cargaHoraria, String tipo, int tolerancia) {
        cursoDAO.inserir(new Curso(0, titulo, new ArrayList<>(), cargaHoraria, tipo, tolerancia));
        return "Curso cadastrado com sucesso!";
    }

    public String atualizarCurso(int idCurso, String titulo, int cargaHoraria, String tipo, int tolerancia) {
        Optional<Curso> encontrado = cursoDAO.buscarPorId(idCurso);
        if (encontrado.isPresent()) {
            Curso atualizado = encontrado.get();
            atualizado.setTitulo(titulo);
            atualizado.setCargaHoraria(cargaHoraria);
            atualizado.setTipo(tipo);
            atualizado.setTolerancia(tolerancia);
            cursoDAO.atualizar(atualizado);
            return "Curso atualizado com sucesso!";
        } else {
            return "Curso com ID " + idCurso + " não encontrado.";
        }
    }

    public String removerCurso(int idCurso) {
        Optional<Curso> encontrado = cursoDAO.buscarPorId(idCurso);
        if (encontrado.isPresent()) {
            cursoDAO.deletar(idCurso);
            return "Curso removido com sucesso!";
        } else {
            return "Curso com ID " + idCurso + " não encontrado.";
        }
    }

    public List<Curso> listarCursos() {
        return cursoDAO.listar();
    }

    public String inserirUC(int idCurso, String nome, int cargaHoraria, int qtdSemestres) {
        Optional<Curso> encontrado = cursoDAO.buscarPorId(idCurso);
        if (encontrado.isPresent()) {
            Curso atualizado = encontrado.get();
            UC uc = new UC(0, nome, cargaHoraria, qtdSemestres);
            cursoDAO.inserirUC(atualizado, uc);
            return "UC inserida em curso de ID " + idCurso + " com sucesso!";
        } else {
            return "Curso com ID " + idCurso + " não encontrado.";
        }
    }
}