package com.senai.control.curso;

import com.senai.model.curso.Curso;
import com.senai.model.curso.DAO.CursoDAO;
import com.senai.model.curso.UC;

import java.util.ArrayList;
import java.util.List;

public class CursoController {
    private final CursoDAO cursoDAO = new CursoDAO();

    public String cadastrarCurso(String titulo, int cargaHoraria, String tipo, int tolerancia) {
        cursoDAO.inserir(new Curso(0, titulo, new ArrayList<>(), cargaHoraria, tipo, tolerancia));
        return "Curso cadastrado.";
    }

    public String atualizarCurso(int idCurso, String titulo, int cargaHoraria, String tipo, int tolerancia ) {
        cursoDAO.atualizar(new Curso(idCurso, titulo, new ArrayList<>(), cargaHoraria, tipo, tolerancia));
        return "Curso atualizado.";
    }

    public String removerCurso(int idCurso) {
        cursoDAO.deletar(idCurso);
        return "Curso removido.";
    }

    public List<Curso> listarCursos(){
        return cursoDAO.listar();
    }
}
