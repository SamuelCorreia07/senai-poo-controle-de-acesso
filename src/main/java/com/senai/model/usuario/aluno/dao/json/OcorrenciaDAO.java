package com.senai.model.usuario.aluno.dao.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.aluno.Ocorrencia;
import com.senai.util.LocalDateAdapter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OcorrenciaDAO {
    private final String caminho = "json_data/ocorrencias.json";
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter()).setPrettyPrinting().create();
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
        ocorrencias.removeIf(o -> o.getId() == id);
        salvar(ocorrencias);
    }

    public List<Ocorrencia> listarTodos() {
        return ocorrencias;
    }

    public Optional<Ocorrencia> buscarPorId(int id) {
        return ocorrencias.stream().filter(o -> o.getId() == id).findFirst();
    }
}
