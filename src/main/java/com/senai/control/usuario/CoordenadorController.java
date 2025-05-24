package com.senai.control.usuario;

import com.senai.model.usuario.Coordenador;
import com.senai.model.usuario.dao.CoordenadorDAO;

import java.util.List;

public class CoordenadorController {
    private final CoordenadorDAO dao = new CoordenadorDAO();

    public void adicionar(Coordenador c) {
        dao.adicionar(c);
    }

    public List<Coordenador> listar() {
        return dao.listar();
    }

    public void atualizar(int id, Coordenador c) {
        dao.atualizar(id, c);
    }

    public void remover(int id) {
        dao.remover(id);
    }

    public Coordenador buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}