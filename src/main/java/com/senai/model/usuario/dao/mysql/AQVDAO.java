package com.senai.model.usuario.dao.mysql;

import com.senai.model.conexaoMySQL.ConexaoMySQL;
import com.senai.model.usuario.AQV;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AQVDAO {
    public void inserir(AQV aqv) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO aqv (id,nome) VALUES (?, ?)");
            stmt.setString(1, aqv.getId());
            stmt.setString(2, aqv.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(AQV aqv) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE aqv SET id = ?,nome = ? WHERE id = ?");
            stmt.setString(1, aqv.getId());
            stmt.setString(2, aqv.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM aqv WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<AQV> buscarPorId(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM aqv WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new AQV(
                        rs.getInt("id"),
                        rs.getString("nome")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public List<AQV> listarTodos() {
        List<AQV> lista = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM aqv");
            while (rs.next()) {
                lista.add(new AQV(
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



