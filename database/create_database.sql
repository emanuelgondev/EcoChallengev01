CREATE DATABASE IF NOT EXISTS ecochallenge;
USE ecochallenge;

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    pontos_totais INT DEFAULT 0,
    nivel_sustentabilidade VARCHAR(50) DEFAULT 'Iniciante',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela de desafios
CREATE TABLE IF NOT EXISTS desafio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    pontos_recompensa INT NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    dica_ecologica TEXT,
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de registros de desafios completados
CREATE TABLE IF NOT EXISTS registro_desafio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    desafio_id INT NOT NULL,
    titulo_desafio VARCHAR(100) NOT NULL,
    pontos_ganhos INT NOT NULL,
    data_realizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    concluido BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (desafio_id) REFERENCES desafio(id) ON DELETE CASCADE,
    INDEX idx_usuario_data (usuario_id, data_realizacao),
    INDEX idx_desafio (desafio_id)
);

-- Inserir desafios padrão
INSERT INTO desafio (id, titulo, descricao, pontos_recompensa, categoria, dica_ecologica) VALUES
(1, 'Economizar água', 'Use um copo reutilizável ao invés de copos descartáveis', 10, 'água', 'Copos descartáveis demoram até 50 anos para se decompor!'),
(2, 'Transporte Sustentável', 'Use transporte público, bicicleta ou caminhe', 15, 'Transporte', 'Carros produzem 4,6 toneladas de CO2 por ano!'),
(3, 'Energia Limpa', 'Desligue aparelhos da tomada quando não usar', 8, 'Energia', 'Aparelhos em standby consomem até 12% da energia da casa!'),
(4, 'Reciclagem', 'Separe o lixo reciclável do orgânico', 12, 'Reciclagem', 'Reciclar 1 tonelada de papel salva 17 árvores!'),
(5, 'Consumo Consciente', 'Compre apenas o necessário hoje', 10, 'Consumo', 'O consumo consciente reduz o desperdício em até 30%!'),
(6, 'Banho Rápido', 'Tome um banho de no máximo 5 minutos', 8, 'água', 'Um banho de 15 minutos gasta até 135 litros de água!'),
(7, 'Sacola Reutilizável', 'Use sacola reutilizável para compras', 10, 'Resíduos', 'Uma sacola plástica demora 400 anos para se decompor!'),
(8, 'Compostagem', 'Separe restos orgânicos para compostagem', 15, 'Resíduos', '30% do lixo doméstico pode virar adubo!')
ON DUPLICATE KEY UPDATE
    titulo = VALUES(titulo),
    descricao = VALUES(descricao),
    pontos_recompensa = VALUES(pontos_recompensa),
    categoria = VALUES(categoria),
    dica_ecologica = VALUES(dica_ecologica);

-- Criar usuário para a aplicação (opcional)
-- CREATE USER 'ecochallenge_user'@'localhost' IDENTIFIED BY 'eco_password_123';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON ecochallenge.* TO 'ecochallenge_user'@'localhost';
-- FLUSH PRIVILEGES;
