package com.senai.model.usuario;

public class AQV extends Usuario {
    private int id;
    private String nomeAluno;
    private String matriculaAluno;
    private String motivoAtraso;
    private String dataRegistro;
    private int idCoordenador;

    public AQV(int id, String nomeAluno, String matriculaAluno, String motivoAtraso, String dataRegistro, int idCoordenador) {
        super(nome, id);
        this.id = id1;
        this.nomeAluno = nomeAluno;
        this.matriculaAluno = matriculaAluno;
        this.motivoAtraso = motivoAtraso;
        this.dataRegistro = dataRegistro;
        this.idCoordenador = idCoordenador;



    }



    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getMotivoAtraso() {
        return motivoAtraso;
    }

    public void setMotivoAtraso(String motivoAtraso) {
        this.motivoAtraso = motivoAtraso;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public int getIdCoordenador() {
        return idCoordenador;
    }

    public void setIdCoordenador(int idCoordenador) {
        this.idCoordenador = idCoordenador;
    }


}