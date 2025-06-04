package com.senai.model.usuario.dao.mysql;

import com.senai.model.ConexaoMySQL.ConexaoMySQL;
import com.senai.model.curso.Ambiente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AmbienteDAO {

    public void inserir(Ambiente ambiente) throws SQLException {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO professor (id, nome, tipo) VALUES (?, ?, ?)");
            stmt.setString(1, ambiente.getId());
            stmt.setString(2, ambiente.getNome());
            stmt.setString(3, ambiente.getTipo());
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