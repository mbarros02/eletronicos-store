-- ============================================================
-- CRIAÇÃO DO BANCO DE DADOS
-- ============================================================
DROP DATABASE IF EXISTS eletronicos_store;
CREATE DATABASE eletronicos_store;
USE eletronicos_store;

-- ============================================================
-- TABELA: grupo_usuario
-- ============================================================
CREATE TABLE grupo_usuario (
    IDGRUPO INT PRIMARY KEY AUTO_INCREMENT,
    NOME ENUM('ADMINISTRADOR', 'ESTOQUISTA') NOT NULL
);

-- ============================================================
-- TABELA: usuarios
-- ============================================================
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

-- ============================================================
-- INSERÇÃO DOS GRUPOS DE USUÁRIOS PADRÃO
-- ============================================================
INSERT INTO grupo_usuario (NOME) VALUES ('ADMINISTRADOR'), ('ESTOQUISTA');

-- ============================================================
-- TABELA: produtos
-- ============================================================
CREATE TABLE produtos (
    IDPRODUTO INT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(200) NOT NULL,
    AVALIACAO DECIMAL(2,1) CHECK (AVALIACAO BETWEEN 1 AND 5),
    DESCRICAO TEXT,
    PRECO DECIMAL(10,2),
    QTD_ESTOQUE INT,
    STATUS TINYINT(1) DEFAULT 1
);

-- ============================================================
-- TABELA: imagem_produto
-- ============================================================
CREATE TABLE imagem_produto (
    IDIMAGEM INT PRIMARY KEY AUTO_INCREMENT,
    CAMINHO VARCHAR(255) NOT NULL,
    PRINCIPAL BOOLEAN DEFAULT FALSE,
    ID_PRODUTO INT,
    FOREIGN KEY (ID_PRODUTO) REFERENCES produtos(IDPRODUTO)
);

-- ============================================================
-- TABELA: clientes
-- ============================================================
CREATE TABLE clientes (
    IDCLIENTE INT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(255) NOT NULL,
    CPF VARCHAR(14) UNIQUE NOT NULL,
    SEXO VARCHAR(10) NOT NULL,
    DATA_NASC DATE NOT NULL,
    EMAIL VARCHAR(255) UNIQUE NOT NULL,
    SENHA VARCHAR(100) NOT NULL
);

-- ============================================================
-- TABELA: enderecos_clientes
-- ============================================================
CREATE TABLE enderecos_clientes (
    IDENDERECO INT PRIMARY KEY AUTO_INCREMENT,
    CEP VARCHAR(10) NOT NULL,
    LOGRADOURO VARCHAR(255),
    NUMERO VARCHAR(10),
    COMPLEMENTO VARCHAR(255),
    BAIRRO VARCHAR(255) NOT NULL,
    LOCALIDADE VARCHAR(255) NOT NULL,
    UF CHAR(2) NOT NULL,
    TIPO_ENDERECO ENUM('F', 'E') NOT NULL,
    STATUS TINYINT(1) DEFAULT 1,
    PRINCIPAL TINYINT(1) DEFAULT 0,
    ID_CLIENTE INT NOT NULL,
    FOREIGN KEY (ID_CLIENTE) REFERENCES clientes(IDCLIENTE)
);

-- ============================================================
-- TABELA: pedidos
-- ============================================================
CREATE TABLE pedidos (
    idpedido INT AUTO_INCREMENT PRIMARY KEY,
    numero_pedido INT UNIQUE,
    idcliente INT NOT NULL,
    endereco_entrega VARCHAR(255),
    forma_pagamento VARCHAR(50),
    nome_cartao VARCHAR(100),
    parcelas VARCHAR(20),
    subtotal DECIMAL(10,2),
    frete DECIMAL(10,2),
    total DECIMAL(10,2),
    status VARCHAR(50) DEFAULT 'Aguardando pagamento',
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idcliente) REFERENCES clientes(idcliente)
);

-- ============================================================
-- TABELA: itens_pedido
-- ============================================================
CREATE TABLE itens_pedido (
    iditem INT AUTO_INCREMENT PRIMARY KEY,
    idpedido INT NOT NULL,
    idproduto INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2),
    subtotal DECIMAL(10,2),
    FOREIGN KEY (idpedido) REFERENCES pedidos(idpedido),
    FOREIGN KEY (idproduto) REFERENCES produtos(idproduto)
);
