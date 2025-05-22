package com.senai.model.curso.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senai.model.curso.Curso;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private List<Curso> cursos;
    private final String caminho = "cursos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Curso> carregar(){
        try(FileReader reader = new FileReader(caminho)){
            Type listType = new TypeToken<ArrayList<Curso>>(){}.getType();
            return gson.fromJson(reader, listType);
        }catch (IOException e){
            return new ArrayList<>();
        }
    }

    public CursoDAO(){
        cursos = carregar();
    }

    private void salvarJson(){
        try(FileWriter writer = new FileWriter(caminho)){
            gson.toJson(cursos, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void inserir(Curso curso){
        int novoId = cursos.stream().mapToInt(Curso::getIdCurso).max().orElse(0) + 1;
        curso.setIdCurso(novoId);
        cursos.add(curso);
        salvarJson();
    }

    public List<Curso> listar(){
        return cursos;
    }

    public void atualizar(Curso curso){
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getIdCurso() == curso.getIdCurso()){
                cursos.set(i, curso);
                break;
            }
            salvarJson();
        }
    }

    public void deletar(int id){
        cursos.removeIf(curso -> curso.getIdCurso() == id);
        salvarJson();
    }


}