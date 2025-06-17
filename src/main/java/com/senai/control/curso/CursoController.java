package com.senai.control.curso;

import com.senai.model.curso.Curso;
import com.senai.model.curso.DAO.json.CursoDAO;
import com.senai.model.curso.UC;
import com.senai.model.usuario.Professor;
import com.senai.model.usuario.dao.json.ProfessorDAO;

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

    public String inserirUC(int idCurso, String nome, int cargaHoraria) {
        Optional<Curso> encontrado = cursoDAO.buscarPorId(idCurso);
        if (encontrado.isPresent()) {
            Curso atualizado = encontrado.get();
            UC uc = new UC(0, nome, cargaHoraria);
            cursoDAO.inserirUC(atualizado, uc);
            return "UC inserida em curso de ID " + idCurso + " com sucesso!";
        } else {
            return "Curso com ID " + idCurso + " não encontrado.";
        }
    }

    public String inserirProfessorUC(int idCurso, int idUC, int idProfessor) {
        Optional<Curso> cursoEncontrado = cursoDAO.buscarPorId(idCurso);
        if (cursoEncontrado.isPresent()) { // Buscando curso por ID
            Curso curso = cursoEncontrado.get();
            Optional<UC> ucEncontrada = cursoDAO.buscarUCPorId(curso, idUC);
            if (ucEncontrada.isPresent()) { // Buscando UC por ID
                ProfessorDAO professorDAO = new ProfessorDAO();
                Optional<Professor> professorEncontrado = professorDAO.buscarPorId(idProfessor);
                if (professorEncontrado.isPresent()) { // Buscando Professor por ID
                    UC uc = ucEncontrada.get(); // instancia UC somente depois de encontrar professor
                    Professor professor = professorEncontrado.get();
                    cursoDAO.inserirProfessorUC(uc, professor);
                    return "Professor de ID " + idProfessor + " foi inserido em UC de ID " + idUC + " de Curso de id " + idCurso + " com sucesso!";
                } else {
                    return "Professor com ID " + idProfessor + " não encontrado.";
                }
            } else {
                return "UC com ID " + idUC + " não encontrada.";
            }
        } else {
            return "Curso com ID " + idCurso + " não encontrado.";
        }
    }
}