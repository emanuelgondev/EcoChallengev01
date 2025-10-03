package com.ecochallenge.dao;

import com.ecochallenge.database.ConexaoSupabase;
import com.ecochallenge.models.RegistroDesafio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações com a tabela RegistroDesafio
 */
public class RegistroDesafioDAO {

    /**
     * Salva um registro de desafio completado
     */
    public boolean salvarRegistroDesafio(RegistroDesafio registro, int usuarioId, int desafioId) {
        String sql = """
            INSERT INTO registro_desafio (usuario_id, desafio_id, titulo_desafio, pontos_ganhos, data_realizacao, concluido)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.setInt(2, desafioId);
            stmt.setString(3, registro.obterTituloDesafio());
            stmt.setInt(4, registro.obterPontosGanhos());
            stmt.setTimestamp(5, Timestamp.valueOf(registro.obterDataRealizacao()));
            stmt.setBoolean(6, registro.isConcluido());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao salvar registro de desafio: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carrega o histórico de desafios de um usuário
     */
    public List<RegistroDesafio> carregarHistoricoPorUsuario(int usuarioId) {
        List<RegistroDesafio> historico = new ArrayList<>();
        String sql = """
            SELECT titulo_desafio, pontos_ganhos, data_realizacao, concluido
            FROM registro_desafio
            WHERE usuario_id = ?
            ORDER BY data_realizacao DESC
            """;

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RegistroDesafio registro = new RegistroDesafio(
                        0,
                        usuarioId,
                        0,
                        rs.getString("titulo_desafio"),
                        rs.getInt("pontos_ganhos"),
                        rs.getTimestamp("data_realizacao").toLocalDateTime(),
                        rs.getBoolean("concluido")
                    );
                    historico.add(registro);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar histórico: " + e.getMessage());
        }

        return historico;
    }

    /**
     * Carrega todo o histórico (para compatibilidade com versão atual)
     */
    public List<RegistroDesafio> carregarHistorico() {
        List<RegistroDesafio> historico = new ArrayList<>();
        String sql = """
            SELECT titulo_desafio, pontos_ganhos, data_realizacao, concluido
            FROM registro_desafio
            ORDER BY data_realizacao DESC
            """;

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RegistroDesafio registro = new RegistroDesafio(
                    0,
                    0,
                    0,
                    rs.getString("titulo_desafio"),
                    rs.getInt("pontos_ganhos"),
                    rs.getTimestamp("data_realizacao").toLocalDateTime(),
                    rs.getBoolean("concluido")
                );
                historico.add(registro);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar histórico: " + e.getMessage());
        }

        return historico;
    }

    /**
     * Conta o total de desafios completados por um usuário
     */
    public int contarDesafiosCompletos(int usuarioId) {
        String sql = "SELECT COUNT(*) FROM registro_desafio WHERE usuario_id = ? AND concluido = TRUE";

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao contar desafios completos: " + e.getMessage());
        }

        return 0;
    }

    /**
     * Conta o total de desafios completados (todos os usuários)
     */
    public int contarTotalDesafiosCompletos() {
        String sql = "SELECT COUNT(*) FROM registro_desafio WHERE concluido = TRUE";

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao contar total de desafios completos: " + e.getMessage());
        }

        return 0;
    }

    /**
     * Conta o total de registros de desafios concluídos por um usuário
     */
    public int contarRegistrosPorUsuario(int usuarioId) {
        String sql = "SELECT COUNT(*) FROM registro_desafio WHERE usuario_id = ?";
        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar registros de desafios: " + e.getMessage());
        }
        return 0;
    }
}
