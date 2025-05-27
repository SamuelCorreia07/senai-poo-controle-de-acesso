package com.senai.model.usuario.aluno.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.aluno.Justificativa;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JustificativaDAO {
    private final String caminho = "justificativas.json";
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).setPrettyPrinting().create();
    private final List<Justificativa> justificativas;

    public JustificativaDAO() {justificativas = carregar();}

    private List<Justificativa> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Justificativa>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<Justificativa> lista) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Justificativa justificativa) {
        int novoId = justificativas.stream().mapToInt(Justificativa::getId).max().orElse(0) + 1;
        justificativa.setId(novoId);
        justificativas.add(justificativa);
        salvar(justificativas);
    }

    public void atualizar(Justificativa justificativa) {
        for (int i = 0; i < justificativas.size(); i++) {
            if (justificativas.get(i).getId() == justificativa.getId()) {
                justificativas.set(i, justificativa);
                break;
            }
        }
        salvar(justificativas);
    }

    public void remover(int id) {
        justificativas.removeIf(p -> p.getId() == id);
        salvar(justificativas);
    }

    public List<Justificativa> listarTodos() {
        return justificativas;
    }

    public Optional<Justificativa> buscarPorId(int id) {
        return carregar().stream().filter(p -> p.getId() == id).findFirst();
    }

}
