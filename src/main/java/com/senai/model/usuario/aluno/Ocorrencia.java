package com.senai.model.usuario.aluno;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ocorrencia {
    private int id;
    private String tipo; //ENTRADA, SAÍDA
    private String descricao;
    private String status; //PENDENTE AQV, PENDENTE PROFESSOR, APROVADO, REPROVADO
    private LocalDateTime dataHora;

    public Ocorrencia(int id, String tipo, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.status = "Pendente AQV";
        this.dataHora = LocalDateTime.now();
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

    public String getDataHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora.format(formatter);
    }
}
