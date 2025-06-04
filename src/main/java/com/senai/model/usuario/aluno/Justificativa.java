package com.senai.model.usuario.aluno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Justificativa {
    private int id;
    private String tipo; // FALTA, OCORRENCIA
    private String descricao;
    private String status; // PENDENTE, APROVADO, REPROVADO
    private LocalDate dataJustificada; // DATA QUE SERÁ JUSTIFICADA
    private LocalDateTime dataHora; // DATA/HORA FEITA A JUSTIFICATIVA
    private int qtdDias;
    private int prazoAceite;

    public Justificativa(int id, String tipo, String descricao, String status, LocalDate dataJustificada, int qtdDias, int prazoAceite) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.status = "Pendente";
        this.dataJustificada = dataJustificada;
        this.dataHora = LocalDateTime.now();
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

    public LocalDate getDataJustificada() {
        return dataJustificada;
    }

    public void setDataJustificada(LocalDate dataJustificada) {
        this.dataJustificada = dataJustificada;
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

    public String getDataJustificadaFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataJustificada.format(formatter);
    }

    public String getDataHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora.format(formatter);
    }
}
