package com.ecochallenge.dao;

import com.ecochallenge.database.ConexaoSupabase;
import com.ecochallenge.models.Usuario;
import java.sql.*;


public class UsuarioDAO {


    public boolean salvarUsuario(Usuario usuario) {
        String sql = """
            INSERT INTO usuario (nome_completo, idade, email, pontos_totais, nivel_sustentabilidade)
            VALUES (?, ?, ?, ?, ?)
            ON CONFLICT (email) DO UPDATE SET
                nome_completo = EXCLUDED.nome_completo,
                idade = EXCLUDED.idade,
                pontos_totais = EXCLUDED.pontos_totais,
                nivel_sustentabilidade = EXCLUDED.nivel_sustentabilidade
            RETURNING id
            """;

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.obterNomeCompleto());
            stmt.setInt(2, usuario.obterIdade());
            stmt.setString(3, usuario.obterEmail());
            stmt.setInt(4, usuario.obterPontosTotais());
            stmt.setString(5, usuario.obterNivelSustentabilidade());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario.definirId(rs.getInt("id"));
                }
            }
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carrega o primeiro usuário encontrado (simulando o comportamento atual)
     */
    public Usuario carregarUsuario() {
        String sql = "SELECT * FROM usuario ORDER BY data_criacao DESC LIMIT 1";

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome_completo"),
                    rs.getInt("idade"),
                    rs.getString("email")
                );
                usuario.definirPontosTotais(rs.getInt("pontos_totais"));
                usuario.definirNivelSustentabilidade(rs.getString("nivel_sustentabilidade"));
                return usuario;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao carregar usuário: " + e.getMessage());
        }

        return null;
    }

    /**
     * Busca usuário por email
     */
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome_completo"),
                        rs.getInt("idade"),
                        rs.getString("email")
                    );
                    usuario.definirPontosTotais(rs.getInt("pontos_totais"));
                    usuario.definirNivelSustentabilidade(rs.getString("nivel_sustentabilidade"));
                    return usuario;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por email: " + e.getMessage());
        }

        return null;
    }

    /**
     * Obtém o ID do usuário pelo email
     */
    public int obterIdUsuario(String email) {
        String sql = "SELECT id FROM usuario WHERE email = ?";

        try (Connection conn = ConexaoSupabase.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter ID do usuário: " + e.getMessage());
        }

        return -1;
    }
}
