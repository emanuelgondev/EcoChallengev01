package com.ecochallenge.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados Supabase (PostgreSQL)
 */
public class ConexaoSupabase {

    // Configurações do banco de dados Supabase
    private static final String URL = "jdbc:postgresql://db.wtxiarcntplgpviljvwz.supabase.co:5432/postgres?sslmode=require";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "$enhaEcoChallenge";

    private static final String DRIVER_CLASS = "org.postgresql.Driver";

    /**
     * Obtém uma conexão com o banco de dados
     * @return Connection objeto de conexão
     * @throws SQLException se houver erro na conexão
     */
    public static Connection obterConexao() throws SQLException {
        try {
            // Carrega o driver JDBC PostgreSQL
            Class.forName(DRIVER_CLASS);

            Properties props = new Properties();
            props.setProperty("user", USUARIO);
            props.setProperty("password", SENHA);
            props.setProperty("ssl", "true");

            return DriverManager.getConnection(URL, props);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado. Adicione postgresql-connector-java ao classpath.", e);
        }
    }

    /**
     * Testa a conexão com o banco de dados
     * @return true se a conexão foi bem-sucedida
     */
    public static boolean testarConexao() {
        try (Connection conn = obterConexao()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Erro ao testar conexão: " + e.getMessage());
            return false;
        }
    }

    /**
     * Fecha uma conexão de forma segura
     * @param conexao a conexão a ser fechada
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
