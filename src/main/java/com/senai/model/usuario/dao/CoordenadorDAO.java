package com.senai.model.usuario.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.Coordenador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CoordenadorDAO {
    private final String filePath = "json_data/coordenadores.json";
    private final Gson gson = new Gson();

    public List<Coordenador> listar() {
        try (Reader reader = new FileReader(filePath)) {
            List<Coordenador> lista = gson.fromJson(reader, new TypeToken<List<Coordenador>>(){}.getType());
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void salvar(List<Coordenador> lista) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(Coordenador c) {
        List<Coordenador> lista = listar();
        c.setId(lista.size() + 1);
        lista.add(c);
        salvar(lista);
    }

    public void atualizar(int id, Coordenador c) {
        List<Coordenador> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                c.setId(id);
                lista.set(i, c);
                salvar(lista);
                return;
            }
        }
    }

    public void remover(int id) {
        List<Coordenador> lista = listar();
        lista.removeIf(c -> c.getId() == id);
        salvar(lista);
    }

    public Coordenador buscarPorId(int id) {
        return listar().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}


