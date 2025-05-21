package com.senai.model.usuario.dao;

import com.google.gson.Gson;
import com.senai.model.usuario.Professor;

import java.util.List;

public class ProfessorDAO {

    private final String caminho = "professores.json";
    private final Gson gson = new Gson();
    private final List<Professor> professores;

    public ProfessorDAO() {

    }

    public Gson getGson() {
        return gson;
    }
}
