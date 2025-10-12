-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS eletronicos_store;
USE eletronicos_store;

-- Criação da tabela de grupos de usuários
CREATE TABLE grupo_usuario (
    IDGRUPO INT PRIMARY KEY AUTO_INCREMENT,
    NOME ENUM('ADMINISTRADOR', 'ESTOQUISTA') NOT NULL
);

-- Criação da tabela de usuários
CREATE TABLE usuarios (
    IDUSUARIO INT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(255) NOT NULL,
    CPF CHAR(14) NOT NULL,
    EMAIL VARCHAR(50) UNIQUE NOT NULL,
    SENHA VARCHAR(255) NOT NULL,
    STATUS TINYINT(1) DEFAULT 1,
    ID_GRUPO_USUARIO INT NOT NULL,
    FOREIGN KEY (ID_GRUPO_USUARIO) REFERENCES grupo_usuario(IDGRUPO)
);

-- Criação da tabela de produtos
CREATE TABLE produtos (
    IDPRODUTO INT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(200) NOT NULL,
    AVALIACAO DECIMAL(2,1) CHECK (AVALIACAO BETWEEN 1 AND 5),
    DESCRICAO TEXT,
    PRECO DECIMAL(10,2),
    QTD_ESTOQUE INT,
    STATUS TINYINT(1) DEFAULT 1
);

-- Criação da tabela de imagens dos produtos
CREATE TABLE imagem_produto (
    IDIMAGEM INT PRIMARY KEY AUTO_INCREMENT,
    CAMINHO VARCHAR(255) NOT NULL,
    PRINCIPAL BOOLEAN DEFAULT FALSE,
    ID_PRODUTO INT,
    FOREIGN KEY (ID_PRODUTO) REFERENCES produtos(IDPRODUTO)
);

-- Inserção de dados de exemplo
INSERT INTO grupo_usuario (NOME) VALUES ('ADMINISTRADOR');
INSERT INTO grupo_usuario (NOME) VALUES ('ESTOQUISTA');

-- Inserção de usuário admin de exemplo (senha: 1234)
INSERT INTO usuarios (NOME, CPF, EMAIL, SENHA, STATUS, ID_GRUPO_USUARIO) VALUES 
('Administrador Global', '711.572.280-38', 'admin@global.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/4.8K.2K', 1, 1);

-- ============================================
-- INSTRUÇÕES DE USO:
-- ============================================
-- 1. Execute este script no MySQL
-- 2. Use as credenciais: admin@global.com / 1234
-- 3. Este usuário tem acesso completo ao sistema (grupo ADMINISTRADOR)
-- 4. Todos os colegas podem usar essas mesmas credenciais
-- 5. Para segurança, considere alterar a senha periodicamente