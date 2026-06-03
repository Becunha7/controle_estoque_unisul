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
- Tamanhos: Pequeno, Médio, Grande
- Embalagens: Lata, Vidro, Plástico

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



