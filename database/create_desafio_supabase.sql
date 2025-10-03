-- Script para criar a tabela desafio no Supabase (PostgreSQL)
CREATE TABLE IF NOT EXISTS desafio (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    pontos_recompensa INTEGER NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    dica_ecologica TEXT,
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Script para criar a tabela registro_desafio no Supabase (PostgreSQL)
CREATE TABLE IF NOT EXISTS registro_desafio (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    desafio_id INTEGER NOT NULL REFERENCES desafio(id) ON DELETE CASCADE,
    titulo_desafio VARCHAR(100) NOT NULL,
    pontos_ganhos INTEGER NOT NULL,
    data_realizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    concluido BOOLEAN DEFAULT TRUE
);

-- Índices auxiliares
CREATE INDEX IF NOT EXISTS idx_usuario_data ON registro_desafio (usuario_id, data_realizacao);
CREATE INDEX IF NOT EXISTS idx_desafio ON registro_desafio (desafio_id);

-- Inserir desafios padrão
INSERT INTO desafio (titulo, descricao, pontos_recompensa, categoria, dica_ecologica, ativo) VALUES
('Economizar água', 'Use um copo reutilizável ao invés de copos descartáveis', 10, 'Água', 'Copos descartáveis demoram até 50 anos para se decompor!', TRUE),
('Transporte Sustentável', 'Use transporte público, bicicleta ou caminhe', 15, 'Transporte', 'Carros produzem 4,6 toneladas de CO2 por ano!', TRUE),
('Energia Limpa', 'Desligue aparelhos da tomada quando não usar', 8, 'Energia', 'Aparelhos em standby consomem até 12% da energia da casa!', TRUE),
('Reciclagem', 'Separe o lixo reciclável do orgânico', 12, 'Resíduos', 'A reciclagem reduz a necessidade de novos aterros sanitários.', TRUE),
('Consumo Consciente', 'Evite o desperdício de alimentos', 7, 'Consumo', 'Planeje suas compras e aproveite sobras.', TRUE),
('Plante uma árvore', 'Plante uma árvore em sua comunidade', 20, 'Meio Ambiente', 'Árvores ajudam a combater o aquecimento global.', TRUE),
('Reduza o uso de plástico', 'Utilize sacolas reutilizáveis', 9, 'Resíduos', 'O plástico pode levar até 400 anos para se decompor.', TRUE),
('Economize energia', 'Troque lâmpadas comuns por LED', 6, 'Energia', 'Lâmpadas LED consomem até 80% menos energia.', TRUE),
('Apoie produtores locais', 'Compre de feiras e pequenos produtores', 11, 'Consumo', 'Produtos locais têm menor pegada de carbono.', TRUE),
('Descarte eletrônico correto', 'Leve eletrônicos antigos a pontos de coleta', 13, 'Resíduos', 'O descarte incorreto pode contaminar o solo e a água.', TRUE);
