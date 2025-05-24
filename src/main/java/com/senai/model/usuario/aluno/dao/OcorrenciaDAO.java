package com.senai.model.usuario.aluno.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.aluno.Ocorrencia;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OcorrenciaDAO {
    private final String caminho = "ocorrencias.json";
    private final Gson gson = new Gson();
    private final List<Ocorrencia> ocorrencias;

    public OcorrenciaDAO() {ocorrencias = carregar();}

    private List<Ocorrencia> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Ocorrencia>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<Ocorrencia> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Ocorrencia ocorrencia) {

        int novoId = ocorrencias.stream().mapToInt(Ocorrencia::getId).max().orElse(0) + 1;
        ocorrencia.setId(novoId);
        ocorrencias.add(ocorrencia);
        salvar(ocorrencias);
    }

    public void atualizar(Ocorrencia ocorrencia) {
        for (int i = 0; i < ocorrencias.size(); i++) {
            if (ocorrencias.get(i).getId() == ocorrencia.getId()) {
                ocorrencias.set(i, ocorrencia);
                break;
            }
        }
        salvar(ocorrencias);
    }

    public void remover(int id) {
        ocorrencias.removeIf(p -> p.getId() == id);
        salvar(ocorrencias);
    }

    public List<Ocorrencia> listarTodos() {
        return ocorrencias;
    }
}
