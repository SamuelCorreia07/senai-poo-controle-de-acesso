package com.senai.model.usuario.aluno;

import java.time.LocalDateTime;

public class Ocorrencia {
    private int id;
    private String tipo; //ENTRADA, SAÍDA
    private String descricao;
    private String status; //PENDENTE AQV, PENDENTE PROFESSOR,
    private LocalDateTime dataHora;

    public Ocorrencia(int id, String tipo, String descricao, LocalDateTime dataHora) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.status = "Pendente AQV";
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
