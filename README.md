# EcoChallenge Terminal v1.0

Sistema backend de desafios sustentáveis que roda no terminal, sem interface gráfica.

## Estrutura do Projeto

```
EcoChallengev01/
├── src/
│   └── com/
│       └── ecochallenge/
│           ├── EcoChallengeTerminal.java (Aplicação principal)
│           ├── models/
│           │   ├── Usuario.java
│           │   ├── Desafio.java
│           │   └── RegistroDesafio.java
│           └── managers/
│               └── GerenciadorDados.java
├── compilar.bat (Script de compilação para Windows)
├── executar.bat (Script de execução para Windows)
└── README.md
```

## Funcionalidades

### 1. Gestão de Usuário
- Criação automática de perfil no primeiro uso
- Atualização de dados pessoais
- Sistema de pontuação e níveis (Iniciante → Intermediário → Avançado → Expert)

### 2. Desafios Sustentáveis
- 8 desafios pré-definidos com categorias:
  - 💧 Água (Economizar Água, Banho Rápido)
  - 🚲 Transporte (Transporte Sustentável)
  - ⚡ Energia (Energia Limpa)
  - ♻️ Reciclagem
  - 🛒 Consumo (Consumo Consciente)
  - 🗑️ Resíduos (Sacola Reutilizável, Compostagem)

### 3. Sistema de Pontuação
- Cada desafio vale pontos diferentes (8-15 pontos)
- Níveis baseados em pontos acumulados:
  - Iniciante: 0-19 pontos
  - Intermediário: 20-49 pontos
  - Avançado: 50-99 pontos
  - Expert: 100+ pontos

### 4. Histórico
- Registro completo de desafios completados
- Data e hora de conclusão
- Pontos ganhos por desafio

### 5. Persistência de Dados
- Dados salvos em arquivos locais (.dat)
- usuario.dat: dados do perfil
- historico.dat: histórico de desafios

## Como Usar

### Windows:
1. Execute `compilar.bat` para compilar o projeto
2. Execute `executar.bat` para rodar a aplicação

### Linux/Mac:
```bash
# Compilar
javac -d . src/com/ecochallenge/*.java src/com/ecochallenge/models/*.java src/com/ecochallenge/managers/*.java

# Executar
java com.ecochallenge.EcoChallengeTerminal
```

## Menu Principal

1. **Ver Desafios Disponíveis** - Lista todos os desafios com descrições e dicas ecológicas
2. **Completar Desafio** - Marcar um desafio como concluído e ganhar pontos
3. **Ver Histórico** - Visualizar todos os desafios já completados
4. **Ver Perfil** - Exibir estatísticas e atualizar dados pessoais
5. **Sair** - Encerrar a aplicação

## Exemplo de Uso

```
=== EcoChallenge Terminal v1.0 ===
Sistema de Desafios Sustentáveis
==================================

Nenhum usuário encontrado. Vamos criar seu perfil!
Digite seu nome completo: João Silva
Digite sua idade: 25
Digite seu e-mail: joao@email.com

Usuário criado com sucesso!

--- Suas Estatísticas ---
Usuário: João Silva (25 anos) - joao@email.com - Pontos: 0 - Nível: Iniciante
Desafios Completos: 0
------------------------

=== MENU PRINCIPAL ===
1. Ver Desafios Disponíveis
2. Completar Desafio
3. Ver Histórico
4. Ver Perfil
5. Sair
Escolha uma opção: 1
```

## Arquivos Gerados

- `usuario.dat`: Dados serializados do usuário
- `historico.dat`: Lista serializada de desafios completados

## Tecnologias

- **Java SE** (puro, sem dependências externas)
- **Serialização Java** para persistência
- **Scanner** para entrada de dados
- **Interface de linha de comando** simples e intuitiva
