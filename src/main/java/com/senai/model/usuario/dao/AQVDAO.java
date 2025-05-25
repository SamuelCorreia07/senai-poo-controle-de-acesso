package com.senai.model.usuario.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.AQV;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AQVDAO {
    private final String filePath = "json_data/aqvs.json";
    private final Gson gson = new Gson();

    public AQVDAO() {
        // Cria diretório e arquivo se não existirem
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                File dir = new File(file.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                file.createNewFile();
                // Escreve lista vazia no arquivo
                try (Writer writer = new FileWriter(filePath)) {
                    gson.toJson(new ArrayList<>(), writer);
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo de dados: " + e.getMessage());
            }
        }
    }

    public List<AQV> listar() {
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<AQV>>() {}.getType();
            List<AQV> lista = gson.fromJson(reader, listType);
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo AQV: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void salvar(List<AQV> lista) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo AQV: " + e.getMessage());
        }
    }

    public void adicionar(AQV a) {
        List<AQV> lista = listar();

        // Garante que o ID seja único
        int nextId = lista.stream()
                .map(AQV::getId)
                .max(Comparator.naturalOrder())
                .orElse(0) + 1;
        a.setId(nextId);

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