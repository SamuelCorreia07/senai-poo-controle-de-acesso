package com.senai.model.curso.DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.curso.Ambiente;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AmbienteDAO {
        private final String caminho = "ambientes.json";
        private final Gson gson = new Gson();
        private final List<Ambiente> ambientes;

        public AmbienteDAO() {
            ambientes = carregar();
        }

        private List<Ambiente> carregar() {
            try (FileReader reader = new FileReader(caminho)) {
                Type listType = new TypeToken<List<Ambiente>>() {}.getType();
                return gson.fromJson(reader, listType);
            } catch (IOException e) {
                return new ArrayList<>();
            }
        }

        private void salvarJson(List<Ambiente> lista) {
            try (FileWriter writer = new FileWriter(caminho)) {
                gson.toJson(lista, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void inserir(Ambiente ambiente) {
            int novoId = ambientes.stream().mapToInt(Ambiente::getId).max().orElse(0) + 1;
            ambiente.setId(novoId);
            ambientes.add(ambiente);
            salvarJson(ambientes);
        }

        public List<Ambiente> listar() {
            return ambientes;
        }

        public void atualizar(Ambiente ambiente) {
            for (int i = 0; i < ambientes.size(); i++) {
                if (ambientes.get(i).getId() == ambiente.getId()) {
                    ambientes.set(i, ambiente);
                    salvarJson(ambientes);
                    return;
                }
            }
        }

        public void deletar(int id) {
            ambientes.removeIf(ambiente -> ambiente.getId() == id);
            salvarJson(ambientes);
        }

        public Optional<Ambiente> buscarPorId(int id) {
            return ambientes.stream().filter(ambiente -> ambiente.getId() == id).findFirst();
        }
    }

