package com.senai.model.usuario.dao.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.Administrador;
import com.senai.model.usuario.Coordenador;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoordenadorDAO {
    private final String caminho = "json_data/coordenadores.json";
    private final Gson gson = new Gson();
    private final List<Coordenador> coordenadores;

    public CoordenadorDAO() {
        coordenadores = carregar();
    }

    private List<Coordenador> carregar() {
        try (FileReader reader = new FileReader(caminho)) {
            Type listType = new TypeToken<List<Coordenador>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public List<Coordenador> listar() {
        return coordenadores;
    }

    public void salvar(List<Coordenador> lista) {
        try (Writer writer = new FileWriter(caminho)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Coordenador coordenador) {
        int nextId = coordenadores.stream().mapToInt(Coordenador::getId).max().orElse(0) + 1;
        coordenador.setId(nextId);
        coordenadores.add(coordenador);
        salvar(coordenadores);
    }

    public void atualizar(Coordenador coordenador) {
        for (int i = 0; i < coordenadores.size(); i++) {
            if (coordenadores.get(i).getId() == coordenador.getId()) {
                coordenadores.set(i, coordenador);
                break;
            }
        }
        salvar(coordenadores);
    }

    public void remover(int id) {
        coordenadores.removeIf(c -> c.getId() == id);
        salvar(coordenadores);
    }

    public Optional<Coordenador> buscarPorId(int id) {
        return coordenadores.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Optional<Coordenador> buscarPorLogin(String login) {
        return coordenadores.stream().filter(a -> a.getLogin().equals(login)).findFirst();
    }
}
