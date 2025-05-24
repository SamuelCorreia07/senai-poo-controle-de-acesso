package com.senai.model.usuario.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.Coordenador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CoordenadorDAO {
    private final String filePath = "data/coordenadores.json";
    private final Gson gson = new Gson();

    public List<Coordenador> getAll() {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, new TypeToken<List<Coordenador>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void Salvar(List<Coordenador> lista) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(Coordenador c) {
        List<Coordenador> lista = getAll();
        lista.add(c);
        Salvar(lista);
    }

    public void update(Coordenador atualizado) {
        List<Coordenador> lista = getAll();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == atualizado.getId()) {
                lista.set(i, atualizado);
                break;
            }
        }
        Salvar(lista);
    }

    public void delete(int id) {
        List<Coordenador> lista = getAll();
        lista.removeIf(c -> c.getId() == id);
        Salvar(lista);
    }
}




