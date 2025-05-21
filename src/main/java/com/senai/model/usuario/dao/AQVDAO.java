package com.senai.model.usuario.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.senai.model.usuario.AQV;

import java.io.*;
import java.util.*;
public class AQVDAO {
    private final String filePath = "data/aqvs.json";
    private final Gson gson = new Gson();

    public List<AQV> getAll() {
        try (Reader reader = new FileReader(filePath)){
            return gson.fromJson(reader, new TypeToken<List<AQV>>() {}.getType());
        } catch (IOException e){
            return new ArrayList<>();
        }

    }
    public void savenALL(List<AQV> aqvs){
        try (Writer writer = new FileWriter(filePath)){
            gson.toJson(aqvs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void add (AQV aqv){
        List<AQV>lista = getAll();
        lista.add(aqv);
        savenALL(lista);
    }
    public  void update(AQV atualizado){
        List<AQV>lista = getAll();
        for (int i = 0; i <lista.size();i++){
            if (lista.get(i).getId()== atualizado.getId()){
                lista.set(i,atualizado);
                break;
            }
        }

    }

}
