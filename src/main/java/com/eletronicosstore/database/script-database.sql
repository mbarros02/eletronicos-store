-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS eletronicos_store;
USE eletronicos_store;

-- Criação da tabela de grupos de usuários
CREATE TABLE IF NOT EXISTS grupo_usuario (
    IDGRUPO INT PRIMARY KEY AUTO_INCREMENT,
    NOME ENUM('ADMINISTRADOR', 'ESTOQUISTA') NOT NULL
);

-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS usuarios (
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
CREATE TABLE IF NOT EXISTS produtos (
    IDPRODUTO INT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(200) NOT NULL,
    AVALIACAO DECIMAL(2,1) CHECK (AVALIACAO BETWEEN 1 AND 5),
    DESCRICAO TEXT,
    PRECO DECIMAL(10,2),
    QTD_ESTOQUE INT
);

-- Criação da tabela de imagens dos produtos
CREATE TABLE IF NOT EXISTS imagem_produto (
    IDIMAGEM INT PRIMARY KEY AUTO_INCREMENT,
    CAMINHO VARCHAR(255) NOT NULL,
    PRINCIPAL BOOLEAN DEFAULT FALSE,
    ID_PRODUTO INT,
    FOREIGN KEY (ID_PRODUTO) REFERENCES produtos(IDPRODUTO)
);

-- Adiciona a coluna status se ainda não existir
ALTER TABLE produtos ADD COLUMN status TINYINT(1) DEFAULT 1;

-- Ativa todos os produtos
UPDATE produtos SET status = 1;
UPDATE produtos SET status=0 WHERE idproduto=1;
UPDATE produtos SET status=1 WHERE idproduto=1;

