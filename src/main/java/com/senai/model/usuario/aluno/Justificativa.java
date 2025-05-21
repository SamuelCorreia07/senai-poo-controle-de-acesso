package com.senai.model.usuario.aluno;

import java.time.LocalDateTime;

public class Justificativa {
    private int id;
    private String tipo; //FALTA, OCCORENCIA
    private String descricao;
    private String status; //PENDENTE, APROVADO, REPROVADO
    private LocalDateTime dataHora;
    private int qtdDias;
    private int prazoAceite;

    public Justificativa(int id, String tipo, String descricao, String status, LocalDateTime dataHora, int qtdDias, int prazoAceite) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.status = status;
        this.dataHora = dataHora;
        this.qtdDias = qtdDias;
        this.prazoAceite = prazoAceite;
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

    public int getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(int qtdDias) {
        this.qtdDias = qtdDias;
    }

    public int getPrazoAceite() {
        return prazoAceite;
    }

    public void setPrazoAceite(int prazoAceite) {
        this.prazoAceite = prazoAceite;
    }
}
