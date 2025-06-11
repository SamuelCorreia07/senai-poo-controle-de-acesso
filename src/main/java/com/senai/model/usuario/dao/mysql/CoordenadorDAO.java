package com.senai.model.usuario.dao.mysql;



import com.senai.model.ConexaoMySQL.ConexaoMySQL;
import com.senai.model.usuario.Coordenador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoordenadorDAO {
    public void inserir(Coordenador coordenador) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO professor (id,nome) VALUES (?, ?)");
            stmt.setInt(1, coordenador.getId());
            stmt.setString(2, coordenador.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Coordenador coordenador) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE coordenador SET id = ?,nome = ? WHERE id = ?");
            stmt.setInt(1, coordenador.getId());
            stmt.setString(2, coordenador.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM coordenador WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<Coordenador> buscarPorId(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM coordenador WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Coordenador(
                        rs.getInt("id"),
                        rs.getString("nome")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public List<Coordenador> listarTodos() {
        List<Coordenador> lista = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM coordenador");
            while (rs.next()) {
                lista.add(new Coordenador(
                        rs.getInt("id"),
                        rs.getString("nome")

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
