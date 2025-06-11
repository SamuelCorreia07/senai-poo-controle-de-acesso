package com.senai.model.curso.DAO.mysql;

import com.senai.model.ConexaoMySQL.ConexaoMySQL;
import com.senai.model.curso.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO {
    public void inserir(Curso curso) throws SQLException {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO curso (id, título, cargaHoraria, tipo, tolerancia) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, curso.getIdCurso());
            stmt.setString(2, curso.getTitulo());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setString(4, curso.getTipo());
            stmt.setTime(5, curso.getTolerancia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Curso curso) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE curso SET id = ?, titulo = ?, cargaHoraria = ?, tipo = ?, tolerancia = ? WHERE id = ?");
            stmt.setInt(1, curso.getIdCurso());
            stmt.setString(2, curso.getTitulo());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setString(4, curso.getTipo());
            stmt.setTime(5, curso.getTolerancia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id){
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM curso WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Curso> buscarPorId(int id){
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM curso WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Curso(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("cargaHoraria"),
                        rs.getString("tipo"),
                        rs.getTime("tolerancia")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Curso> listarTodos(){
        List<Curso> lista = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM curso");
            while (rs.next()) {
                lista.add(new Curso(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("cargaHoraria"),
                        rs.getString("tipo"),
                        rs.getTime("tolerancia")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

