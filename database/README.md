# Configuração do Banco de Dados MySQL para EcoChallenge

## Pré-requisitos

1. **MySQL Server** instalado e rodando
2. **MySQL Connector/J** (driver JDBC) no classpath do projeto

## Configuração do Banco

### 1. Executar o script SQL
Execute o arquivo `database/create_database.sql` no seu MySQL:

```bash
mysql -u root -p < database/create_database.sql
```

Ou copie e cole o conteúdo no MySQL Workbench/phpMyAdmin.

### 2. Configurar credenciais
Edite o arquivo `src/com/ecochallenge/database/ConexaoMySQL.java` e altere:

```java
private static final String USUARIO = "seu_usuario_mysql";
private static final String SENHA = "sua_senha_mysql";
```

### 3. Baixar o MySQL Connector/J

Baixe o driver JDBC do MySQL em:
https://dev.mysql.com/downloads/connector/j/

Adicione o arquivo `.jar` ao classpath do projeto.

## Estrutura do Banco

### Tabelas criadas:

- **usuario**: Armazena dados dos usuários
- **desafio**: Contém os desafios disponíveis
- **registro_desafio**: Histórico de desafios completados

### Dados iniciais:

O script já insere os 8 desafios padrão do sistema.

## Migração dos dados existentes

Se você já tem dados nos arquivos `.dat`, eles não serão migrados automaticamente. 
O sistema começará "limpo" com o banco MySQL.

## Verificação

O sistema testa a conexão automaticamente na inicialização e exibe avisos se houver problemas.
