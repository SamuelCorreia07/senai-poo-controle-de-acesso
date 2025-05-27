package com.senai.model.usuario.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.Coordenador;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoordenadorDAO {
    private final String filePath = "json_data/coordenadores.json";
    private final Gson gson = new Gson();
    private Coordenador[] coordenadores;

    public CoordenadorDAO() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                File dir = new File(file.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file.createNewFile();


                try (Writer writer = new FileWriter(filePath)) {
                    gson.toJson(new ArrayList<Coordenador>(), writer);
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo de dados: " + e.getMessage());
            }
        }
    }

    public List<Coordenador> listar() {
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Coordenador>>() {}.getType();
            List<Coordenador> lista = gson.fromJson(reader, listType);
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo Coordenador: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Erro ao interpretar JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void salvar(List<Coordenador> lista) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo Coordenador: " + e.getMessage());
        }
    }

    public void adicionar(Coordenador coordenador) {
        List<Coordenador> lista = listar();


        int nextId = lista.stream()
                .map(Coordenador::getId)
                .max(Comparator.naturalOrder())
                .orElse(0) + 1;

        coordenador.setId(nextId);
        lista.add(coordenador);
        salvar(lista);
    }

    public void atualizar(int id, Coordenador coordenador) {
        List<Coordenador> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                coordenador.setId(id);
                lista.set(i, coordenador);
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
        for (Coordenador c : coordenadores) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
