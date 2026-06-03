# Sistema de Controle de Estoque 📦

Sistema completo de gestão de estoque desenvolvido em Java com interface gráfica (Swing) e banco de dados MySQL.

## 👥 Integrantes
- Bernardo Soares da Cunha - 10726113657 - Git: https://github.com/Becunha7
- Lucas da Silveira Gentil - 10725213757 - Git: https://github.com/gentillucas05

---

## 📋 Requisitos Implementados

### ✅ CRUD Completo

#### 1. Produtos
- Cadastrar novos produtos com informações completas
- Listar produtos em ordem alfabética
- Atualizar informações de produtos
- Excluir produtos do sistema
- Validação de quantidade em estoque

#### 2. Categorias
- Cadastrar categorias com tamanho e tipo de embalagem
- Listar categorias disponíveis
- Atualizar dados de categorias
- Excluir categorias


#### 3. Movimentações (Entrada/Saída)
- Registrar entradas de estoque
- Registrar saídas de estoque
- Histórico completo de movimentações
- Validação de quantidade antes de saída
- Transações ACID no banco de dados

### 📊 Relatórios Implementados

1. **Lista de Preços**
   - Todos os produtos em ordem alfabética
   - Preço unitário, unidade de medida e categoria
   - Ideal para consultar precificação

2. **Balanço Físico/Financeiro**
   - Quantidade de cada produto em estoque
   - Valor total por produto (quantidade × preço)
   - Valor total do estoque (somatório)
   - Análise financeira completa

3. **Produtos Abaixo do Mínimo**
   - Produtos com estoque abaixo do limite mínimo
   - Nome, quantidade mínima, quantidade em estoque e categoria
   - Alerta para reposição

4. **Quantidade de Produtos por Categoria**
   - Total de produtos distintos em cada categoria
   - Análise por categoria
   - Ordenação alfabética

5. **Produtos Mais Movimentados**
   - Produto com maior quantidade de entrada
   - Produto com maior quantidade de saída
   - Análise de movimentação

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 25+
- **GUI:** Swing (Java GUI Framework)
- **Banco de Dados:** MySQL 8.0+
- **Gerenciador de Projetos:** Maven
- **Driver JDBC:** MySQL Connector J 8.0.33
- **IDE Recomendada:** NetBeans

---

## Banco de dados
user:root
password:root

---
📐 Requisitos Funcionais (RF)
RF01 — Gerenciamento de Categorias

Cadastrar novas categorias informando nome, tamanho e tipo de embalagem.
Listar todas as categorias disponíveis.
Editar dados de uma categoria existente.
Excluir categorias do sistema.

RF02 — Gerenciamento de Produtos

Cadastrar novos produtos com nome, unidade de medida, preço, quantidade inicial, quantidade mínima, quantidade máxima e categoria.
Listar produtos em ordem alfabética.
Editar informações de um produto existente.
Excluir produtos do sistema.
Exigir que ao menos uma categoria esteja cadastrada antes de salvar um produto.

RF03 — Movimentações de Estoque

Registrar entradas de estoque com data e quantidade.
Registrar saídas de estoque com data e quantidade.
Exibir histórico completo de movimentações.
Impedir saída quando a quantidade solicitada for maior que o estoque disponível.
Atualizar automaticamente a quantidade em estoque após cada movimentação.

RF04 — Relatórios

Lista de Preços: exibir todos os produtos com preço unitário, unidade e categoria, em ordem alfabética.
Balanço Físico/Financeiro: exibir quantidade em estoque, valor por produto e valor total do estoque.
Produtos Abaixo do Mínimo: listar produtos com estoque abaixo da quantidade mínima cadastrada.
Produtos por Categoria: exibir o total de produtos distintos em cada categoria.
Produtos Mais Movimentados: identificar o produto com maior volume de entradas e o com maior volume de saídas.


🔒 Requisitos Não Funcionais (RNF)
RNF01 — Integridade de Dados

Todas as operações de movimentação são executadas em transações ACID (commit/rollback), garantindo consistência no banco de dados.

RNF02 — Validação de Entradas

Campos obrigatórios são validados antes de salvar (nome vazio, campos numéricos inválidos, ausência de categoria).
O sistema exibe mensagens de alerta claras ao usuário em caso de erro de preenchimento.

RNF03 — Usabilidade

Interface construída com Java Swing, com fontes padronizadas (Segoe UI) e botões com cores distintas por ação.
Janelas não redimensionáveis e centralizadas na tela para consistência visual.

RNF04 — Manutenibilidade

Código organizado em camadas: modelo (entidades), dao (acesso a dados), visao (interface), db (conexão) e enums.
Uso de PreparedStatement em todas as consultas SQL para evitar SQL Injection.

RNF05 — Portabilidade

Projeto gerenciado com Maven, permitindo compilação e execução em qualquer sistema operacional com Java instalado.



