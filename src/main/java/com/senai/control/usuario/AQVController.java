package com.senai.control.usuario;

import com.senai.model.usuario.AQV;
import com.senai.model.usuario.dao.json.AQVDAO;

import java.util.List;

public class AQVController {
    private final AQVDAO dao = new AQVDAO();

    public void adicionar(AQV a) {
        dao.inserir(a);
    }

    public List<AQV> listar() {
        return dao.listarTodos();
    }

    public void atualizar(int id, AQV a) {
        dao.atualizar(id, a);
    }

    public void remover(int id) {
        dao.remover(id);
    }

    public AQV buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public List<AQV> listarPorCoordenador(int id) {
        return null;
    }
}