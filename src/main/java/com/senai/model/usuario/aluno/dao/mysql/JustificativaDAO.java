package com.senai.model.usuario.aluno.dao.mysql;

import com.senai.model.conexaoMySQL.ConexaoMySQL;
import com.senai.model.usuario.AQV;
import com.senai.model.usuario.aluno.Justificativa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JustificativaDAO {
    public void inserir(Justificativa justificativa) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO justificativa (id,nome) VALUES (?, ?)");
            stmt.setString(1, justificativa.getId());
            stmt.setString(2, justificativa.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Justificativa justificativa) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE justificativa SET id = ?,nome = ? WHERE id = ?");
            stmt.setString(1, justificativa.getId());
            stmt.setString(2, justificativa.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM justificativa WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Optional<Justificativa> buscarPorId(int id) {
        try (Connection conn = ConexaoMySQL.conectar()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM justificativa WHERE id = ?");
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
    public List<Justificativa> listarTodos() {
        List<Justificativa> lista = new ArrayList<>();
        try (Connection conn = ConexaoMySQL.conectar()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM justificativa");
            while (rs.next()) {
                lista.add(new Justificativa(
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

}
