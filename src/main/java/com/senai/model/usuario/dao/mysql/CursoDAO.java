package com.senai.model.usuario.dao.mysql;

import com.senai.model.ConexaoMySQL.ConexaoMySQL;
import com.senai.model.curso.Ambiente;
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
            stmt.set(5, curso.getTolerancia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Ambiente ambiente) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE ambiente SET id = ?, nome = ?, tipo = ? WHERE id = ?");
            stmt.setString(1, ambiente.getId());
            stmt.setString(2, ambiente.getNome());
            stmt.setString(3, ambiente.getTipo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id){
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM ambiente WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Ambiente> buscarPorId(int id){
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ambiente WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Ambiente(
                        rs.getInt("id"),
                        rs.getString("nome")
                        rs.getString("tipo")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Ambiente> listarTodos(){
        List<Ambiente> lista = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ambiente");
            while (rs.next()) {
                lista.add(new Ambiente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
}
