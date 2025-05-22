package com.senai.control.usuario;

import com.senai.model.usuario.AQV;
import com.senai.model.usuario.dao.AQVDAO;

import java.util.List;

public class AQVController {
    private AQVDAO dao = new AQVDAO();

    public void adicionarAQV(AQV aqv) {
        dao.add(aqv);
        }
        public List<AQV> listarTodos(){
        return dao.getAll();
        }
        public void atualizarAQV(AQV aqv) {
        dao.update(aqv);
        }
        public void removerAQV(int id){
        dao.delete(id);
        }

    }


