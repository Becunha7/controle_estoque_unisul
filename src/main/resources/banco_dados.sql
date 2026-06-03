-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS controle_estoque_unisul;
USE controle_estoque_unisul;

-- Tabela de Categorias
CREATE TABLE IF NOT EXISTS categoria (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tamanho VARCHAR(20) NOT NULL,
    embalagem VARCHAR(20) NOT NULL
);

-- Tabela de Produtos
CREATE TABLE IF NOT EXISTS produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_categoria INT NOT NULL,
    nome VARCHAR(150) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    unidade VARCHAR(20) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    qntd_min INT NOT NULL,
    qntd_max INT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

-- Tabela de Movimentações
CREATE TABLE IF NOT EXISTS movimentacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_produto INT NOT NULL,
    data_movimentacao DATE NOT NULL,
    qntd_movimentada INT NOT NULL,
    tipo_movimentacao VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);
