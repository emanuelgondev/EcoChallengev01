-- Script para criar a tabela usuario no Supabase (PostgreSQL)
CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    idade INTEGER NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    pontos_totais INTEGER DEFAULT 0,
    nivel_sustentabilidade VARCHAR(50),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

