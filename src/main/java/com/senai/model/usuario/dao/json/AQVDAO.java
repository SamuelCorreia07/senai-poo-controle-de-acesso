package com.senai.model.usuario.dao.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.AQV;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AQVDAO {
    private final String caminho = "json_data/aqvs.json";
    private final Gson gson = new Gson();
    private List<AQV> aqvs;

    public AQVDAO() {
        this.aqvs = carregar();
    }

    private List<AQV> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<AQV>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvar() {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(aqvs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<AQV> listarTodos() {
        return aqvs;
    }

    public void inserir(AQV aqv) {
        int nextId = aqvs.stream().mapToInt(AQV::getId).max().orElse(0) + 1;
        aqv.setId(nextId);
        aqvs.add(aqv);
        salvar();
    }

    public void atualizar(AQV aqv) {
        for (int i = 0; i < aqvs.size(); i++) {
            if (aqvs.get(i).getId() == aqv.getId()) {
                aqvs.set(i, aqv);
                break;
            }
        }
        salvar();
    }

    public void remover(int id) {
        aqvs.removeIf(a -> a.getId() == id);
        salvar();
    }

    public Optional<AQV> buscarPorId(int id) {
        return aqvs.stream().filter(a -> a.getId() == id).findFirst();
    }

    public Optional<AQV> buscarPorLogin(String login) {
        return aqvs.stream().filter(a -> a.getLogin().equals(login)).findFirst();
    }
}