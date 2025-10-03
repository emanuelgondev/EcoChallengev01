package com.ecochallenge.dao;

import com.ecochallenge.database.ConexaoSupabase;
import com.ecochallenge.models.Desafio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações com a tabela Desafio
 */
public class DesafioDAO {

    /**
     * Obtém todos os desafios disponíveis
     */
    public List<Desafio> obterDesafiosDisponiveis() {
        List<Desafio> desafios = new ArrayList<>();
        String sql = "SELECT * FROM desafio WHERE ativo = TRUE ORDER BY categoria, titulo";

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Desafio desafio = new Desafio(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getInt("pontos_recompensa"),
                    rs.getString("categoria"),
                    rs.getString("dica_ecologica"),
                    rs.getBoolean("ativo")
                );
                desafios.add(desafio);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar desafios: " + e.getMessage());
        }

        return desafios;
    }

    /**
     * Busca um desafio por ID
     */
    public Desafio buscarPorId(int id) {
        String sql = "SELECT * FROM desafio WHERE id = ?";

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Desafio(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("pontos_recompensa"),
                        rs.getString("categoria"),
                        rs.getString("dica_ecologica"),
                        rs.getBoolean("ativo")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar desafio por ID: " + e.getMessage());
        }

        return null;
    }

    /**
     * Adiciona um novo desafio
     */
    public boolean adicionarDesafio(Desafio desafio) {
        String sql = """
            INSERT INTO desafio (titulo, descricao, pontos_recompensa, categoria, dica_ecologica)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, desafio.obterTitulo());
            stmt.setString(2, desafio.obterDescricao());
            stmt.setInt(3, desafio.obterPontosRecompensa());
            stmt.setString(4, desafio.obterCategoria());
            stmt.setString(5, desafio.obterDicaEcologica());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar desafio: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtém desafios por categoria
     */
    public List<Desafio> obterDesafiosPorCategoria(String categoria) {
        List<Desafio> desafios = new ArrayList<>();
        String sql = "SELECT * FROM desafio WHERE categoria = ? AND ativo = TRUE ORDER BY titulo";

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Desafio desafio = new Desafio(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getInt("pontos_recompensa"),
                        rs.getString("categoria"),
                        rs.getString("dica_ecologica"),
                        rs.getBoolean("ativo")
                    );
                    desafios.add(desafio);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar desafios por categoria: " + e.getMessage());
        }

        return desafios;
    }
}
