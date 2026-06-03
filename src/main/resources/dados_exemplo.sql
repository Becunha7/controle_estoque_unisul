-- Dados de exemplo para teste do sistema

USE controle_estoque_unisul;

-- Inserir categorias de exemplo
INSERT INTO categoria (nome, tamanho, embalagem) VALUES
('Alimentos Secos', 'PEQUENO', 'PLASTICO'),
('Bebidas', 'MEDIO', 'LATA'),
('Produtos de Limpeza', 'GRANDE', 'PLASTICO'),
('Eletrônicos', 'PEQUENO', 'VIDRO');

-- Inserir produtos de exemplo
INSERT INTO produto (id_categoria, nome, quantidade, unidade, preco, qntd_min, qntd_max) VALUES
(1, 'Arroz Integral', 50, 'kg', 8.50, 10, 100),
(1, 'Feijão Carioca', 35, 'kg', 7.20, 10, 80),
(2, 'Suco Natural Laranja', 120, 'L', 5.00, 20, 150),
(2, 'Refrigerante Cola', 80, 'L', 3.50, 15, 100),
(3, 'Detergente Neutro', 200, 'Un', 2.50, 30, 300),
(3, 'Pano de Chão', 150, 'Un', 5.00, 20, 200),
(4, 'Mouse Sem Fio', 25, 'Un', 45.00, 5, 50),
(4, 'Teclado USB', 30, 'Un', 89.90, 5, 40);

-- Inserir movimentações de exemplo
INSERT INTO movimentacao (id_produto, data_movimentacao, qntd_movimentada, tipo_movimentacao) VALUES
(1, '2025-12-01', 20, 'ENTRADA'),
(1, '2025-12-05', 5, 'SAIDA'),
(2, '2025-12-02', 15, 'ENTRADA'),
(3, '2025-12-03', 30, 'SAIDA'),
(4, '2025-12-04', 20, 'ENTRADA'),
(5, '2025-12-05', 50, 'ENTRADA'),
(5, '2025-12-06', 10, 'SAIDA'),
(7, '2025-12-07', 8, 'ENTRADA'),
(7, '2025-12-08', 2, 'SAIDA'),
(8, '2025-12-09', 10, 'ENTRADA');
