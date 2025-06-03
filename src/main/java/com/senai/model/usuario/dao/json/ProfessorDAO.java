package com.senai.model.usuario.dao.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.Professor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorDAO {

    private final String caminho = "json_data/professores.json";
    private final Gson gson = new Gson();
    private final List<Professor> professores;

    public ProfessorDAO(){
        professores = carregar();
    }

    private List<Professor> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Professor>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    private void salvarJson(List<Professor> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Professor professor) {

        int novoId = professores.stream().mapToInt(Professor::getId).max().orElse(0) + 1;
        professor.setId(novoId);
        professores.add(professor);
        salvarJson(professores);
    }
    public List<Professor> listar() {
        return professores;
    }

    public void atualizar(Professor professor) {
        for (int i = 0; i < professores.size(); i++) {
            if (professores.get(i).getId() == professor.getId()) {
                professores.set(i, professor);
                break;
            }
        }
        salvarJson(professores);
    }

    public void deletar(int id) {
        professores.removeIf(professor -> professor.getId() == id);
        salvarJson(professores);
    }

    public Optional<Professor> buscarPorId(int id) {
        return carregar().stream().filter(professor -> professor.getId() == id).findFirst();
    }

    }



