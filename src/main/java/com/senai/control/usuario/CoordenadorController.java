package com.senai.control.usuario;

import com.senai.model.usuario.Coordenador;
import com.senai.model.usuario.dao.CoordenadorDAO;

public class CoordenadorController {
    private CoordenadorDAO dao = new CoordenadorDAO();

    public void adicionar(Coordenador c){
        dao.add(c);
    }
}
