package com.senai.model.usuario.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.Coordenador;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CoordenadorDAO {
    private final String filePath = "data/coordenadores.json";
    private final Gson gson = new Gson();

    public List <Coordenador> getAll(){
        try (Reader reader = new FileReader(filePath)){
            return gson.fromJson(reader, new TypeToken<List<Coordenador>>(){}.getType());
        }catch (IOException e) {
            return new ArrayList<>();
        }
    }


}
