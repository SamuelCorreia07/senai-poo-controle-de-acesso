package com.senai.model.usuario.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.AQV;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AQVDAO {
    private final String filePath = "json_data/aqvs.json";
    private final Gson gson = new Gson();

    public List<AQV> listar() {
        try (Reader reader = new FileReader(filePath)) {
            List<AQV> lista = gson.fromJson(reader, new TypeToken<List<AQV>>(){}.getType());
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void salvar(List<AQV> lista) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(AQV a) {
        List<AQV> lista = listar();
        a.setId(lista.size() + 1);
        lista.add(a);
        salvar(lista);
    }

    public void atualizar(int id, AQV a) {
        List<AQV> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                a.setId(id);
                lista.set(i, a);
                salvar(lista);
                return;
            }
        }
    }

    public void remover(int id) {
        List<AQV> lista = listar();
        lista.removeIf(a -> a.getId() == id);
        salvar(lista);
    }

    public AQV buscarPorId(int id) {
        return listar().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }
}