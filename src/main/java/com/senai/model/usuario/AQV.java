package com.senai.model.usuario;

public class AQV extends Usuario {
    private int id;
    private String nomePessoa;
    private String setor;
    private String dataHoraAcesso;

    public AQV(int nome, int id, int id1, String nomePessoa, String setor, String dataHoraAcesso) {
        super(nome, id);
        this.id = id1;
        this.nomePessoa = nomePessoa;
        this.setor = setor;
        this.dataHoraAcesso = dataHoraAcesso;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDataHoraAcesso() {
        return dataHoraAcesso;
    }

    public void setDataHoraAcesso(String dataHoraAcesso) {
        this.dataHoraAcesso = dataHoraAcesso;
    }

    @Override
    public String toString() {
        return "AQV{" +
                "id=" + id +
                ", nomePessoa='" + nomePessoa + '\'' +
                ", setor='" + setor + '\'' +
                ", dataHoraAcesso='" + dataHoraAcesso + '\'' +
                '}';
    }
}
