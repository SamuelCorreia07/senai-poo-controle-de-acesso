package com.senai.model.turma.DAO.mysql;

import com.senai.model.ConexaoMySQL.ConexaoMySQL;
import com.senai.model.turma.Turma;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmaDAO {
    public void inserir(Turma turma) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO turma (idTurma, nome, curso, subturmas, dataInicio, qtdSemestre, horarioEntrada) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, turma.getIdTurma());
            stmt.setString(2, turma.getNome());
            stmt.setString(3, turma.getCurso());
            stmt.setInt(4, turma.getSubturmas());
            stmt.setString(5, turma.getDataInicio());
            stmt.setInt(6, turma.getQtdSemestre());
            stmt.setString(7, turma.getHorarioEntrada());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Turma turma) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE turma SET idTurma = ?, nome = ?, curso = ?, subturmas = ?, dataInicio = ?, qtdSemestre = ?, horarioEntrada = ? WHERE id = ?");
            stmt.setInt(1, turma.getIdTurma());
            stmt.setString(2, turma.getNome());
            stmt.setString(3, turma.getCurso());
            stmt.setInt(4, turma.getSubturmas());
            stmt.setString(5, turma.getDataInicio());
            stmt.setInt(6, turma.getQtdSemestre());
            stmt.setString(7, turma.getHorarioEntrada());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM turma WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<Turma> buscarPorId(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM turma WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Turma(
                        rs.getInt("idTurma"),
                        rs.getString("nome"),
                        rs.getString("Curso"),
                        rs.getInt("Subturmas"),
                        rs.getString("DataInicio"),
                        rs.getString("QtdSemestre"),
                        rs.getString("HorarioEntrada")


                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public List<Turma> listarTodos() {
        List<Turma> lista = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM turma");
            while (rs.next()) {
                lista.add(new Turma(
                        rs.getInt("idTurma"),
                        rs.getString("nome"),
                        rs.getString("Curso"),
                        rs.getInt("Subturmas"),
                        rs.getString("DataInicio"),
                        rs.getString("QtdSemestre"),
                        rs.getString("HorarioEntrada")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}