package com.senai.control.usuario;

import com.senai.model.usuario.Coordenador;
import com.senai.model.usuario.dao.CoordenadorDAO;

import java.util.List;

public class CoordenadorController {
    private CoordenadorDAO dao = new CoordenadorDAO();

    public void adicionar(Coordenador c){
        dao.add(c);
    }
    public List<Coordenador> listarTodos() {
        return dao.getAll();
    }

    public void atualizar(Coordenador c) {
        dao.update(c);
    }

    public void remover(int id) {
        dao.delete(id);
    }
}

