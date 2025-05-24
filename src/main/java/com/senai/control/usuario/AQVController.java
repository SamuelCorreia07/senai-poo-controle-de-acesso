package com.senai.control.usuario;

import com.senai.model.usuario.AQV;
import com.senai.model.usuario.dao.AQVDAO;

import java.util.List;

public class AQVController {
    private final AQVDAO dao = new AQVDAO();

    public void adicionar(AQV a) {
        dao.adicionar(a);
    }

    public List<AQV> listar() {
        return dao.listar();
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
}