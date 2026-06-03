# Arquitetura e Fluxo do Sistema

## 🏗️ Arquitetura em Camadas

```
┌─────────────────────────────────────────┐
│   APRESENTAÇÃO (Swing UI)               │
│  ┌──────────────────────────────────┐   │
│  │ FrmMenuPrincipal                 │   │
│  │ ├─ FrmCadastroCategoria          │   │
│  │ ├─ FrmCadastroProduto            │   │
│  │ ├─ FrmCadastroMovimentacao       │   │
│  │ ├─ FrmGerenciaCategoria          │   │
│  │ ├─ FrmGerenciaProduto            │   │
│  │ ├─ FrmGerenciaMovimentacao       │   │
│  │ └─ FrmRelatorios (Novidade!)     │   │
│  └──────────────────────────────────┘   │
└─────────────────────────────────────────┘
               ↓
┌─────────────────────────────────────────┐
│   LÓGICA DE NEGÓCIO (DAO)               │
│  ┌──────────────────────────────────┐   │
│  │ ProdutoDAO                       │   │
│  │ ├─ cadastrar()                   │   │
│  │ ├─ listar()                      │   │
│  │ ├─ listarAbaixoDoMinimo() ✨     │   │
│  │ ├─ listarBalancoFinanceiro() ✨  │   │
│  │ ├─ listarPrecos() ✨             │   │
│  │ ├─ getTotalEstoque() ✨          │   │
│  │ ├─ atualizar()                   │   │
│  │ └─ excluir()                     │   │
│  │                                  │   │
│  │ CategoriaDAO                     │   │
│  │ ├─ cadastrar()                   │   │
│  │ ├─ listar()                      │   │
│  │ ├─ listarQuantidadeProdutosPor.. │   │
│  │ │  Categoria() ✨                │   │
│  │ ├─ atualizar()                   │   │
│  │ └─ excluir()                     │   │
│  │                                  │   │
│  │ MovimentacaoDAO                  │   │
│  │ ├─ registrar()                   │   │
│  │ ├─ listar()                      │   │
│  │ ├─ listarPorProduto()            │   │
│  │ └─ produtoMaisEntradaSaida() ✨  │   │
│  └──────────────────────────────────┘   │
└─────────────────────────────────────────┘
               ↓
┌─────────────────────────────────────────┐
│   MODELOS (Entidades)                   │
│  ┌──────────────────────────────────┐   │
│  │ Produto                          │   │
│  │ ├─ id                            │   │
│  │ ├─ nome                          │   │
│  │ ├─ preco                         │   │
│  │ ├─ quantidade                    │   │
│  │ └─ ... (7 atributos)             │   │
│  │                                  │   │
│  │ Categoria                        │   │
│  │ ├─ id                            │   │
│  │ ├─ nome                          │   │
│  │ ├─ tamanho (enum)                │   │
│  │ └─ embalagem (enum)              │   │
│  │                                  │   │
│  │ Movimentacao                     │   │
│  │ ├─ id                            │   │
│  │ ├─ id_produto                    │   │
│  │ ├─ data                          │   │
│  │ ├─ quantidade                    │   │
│  │ └─ tipo (ENTRADA/SAIDA)          │   │
│  └──────────────────────────────────┘   │
└─────────────────────────────────────────┘
               ↓
┌─────────────────────────────────────────┐
│   PERSISTÊNCIA (Banco de Dados)         │
│  ┌──────────────────────────────────┐   │
│  │ MySQL 8.0+                       │   │
│  │ ├─ categoria                     │   │
│  │ ├─ produto                       │   │
│  │ └─ movimentacao                  │   │
│  └──────────────────────────────────┘   │
└─────────────────────────────────────────┘

✨ = Novos métodos adicionados para relatórios
```

---

## 📊 Fluxo de Dados dos Relatórios

### 1. Lista de Preços
```
FrmRelatorios
    ↓
btnListaPrecos.actionPerformed()
    ↓
gerarListaPrecos()
    ↓
ProdutoDAO.listarPrecos()
    ↓
SELECT p.nome, p.preco, p.unidade, c.nome FROM produto p JOIN categoria c
    ↓
Retorna List<Object[]>
    ↓
Popula JTable com formatação de moeda (R$)
```

### 2. Balanço Financeiro
```
FrmRelatorios
    ↓
btnBalancoFinanceiro.actionPerformed()
    ↓
gerarBalancoFinanceiro()
    ↓
ProdutoDAO.listarBalancoFinanceiro()
    ↓
SELECT nome, unidade, quantidade, preco, (quantidade * preco) AS total FROM produto
    ↓
Calcula totalEstoque (soma de todos os totais)
    ↓
Popula JTable + linha de totais
    ↓
Exibe JOptionPane com valor total
```

### 3. Produtos Abaixo do Mínimo
```
FrmRelatorios
    ↓
btnProdutosMinimo.actionPerformed()
    ↓
gerarProdutosAbaixoMinimo()
    ↓
ProdutoDAO.listarAbaixoDoMinimo()
    ↓
SELECT * FROM produto WHERE quantidade < qntd_min ORDER BY nome
    ↓
Retorna List<Produto>
    ↓
Popula JTable
```

### 4. Quantidade de Produtos por Categoria
```
FrmRelatorios
    ↓
btnProdutosPorCategoria.actionPerformed()
    ↓
gerarProdutosPorCategoria()
    ↓
CategoriaDAO.listarQuantidadeProdutosPorCategoria()
    ↓
SELECT c.nome, COUNT(DISTINCT p.id) FROM categoria c LEFT JOIN produto p GROUP BY c.id
    ↓
Retorna List<Object[]>
    ↓
Popula JTable
```

### 5. Produtos Mais Movimentados
```
FrmRelatorios
    ↓
btnProdutosMaisMovimentados.actionPerformed()
    ↓
gerarProdutosMaisMovimentados()
    ↓
MovimentacaoDAO.produtoMaisEntradaSaida()
    ↓
Executa 2 queries:
  1. SELECT produto COM MAIOR ENTRADA
  2. SELECT produto COM MAIOR SAIDA
    ↓
Retorna String[2]
    ↓
Popula JTable com 2 linhas (Entrada/Saida)
```

---

## 🔄 Fluxo de Movimentação (Entrada/Saída)

```
Usuário clica em "Cadastrar Movimentação"
    ↓
FrmCadastroMovimentacao abre
    ↓
Usuário seleciona:
  - Produto
  - Tipo (ENTRADA ou SAIDA)
  - Data
  - Quantidade
    ↓
Usuário clica "Salvar"
    ↓
MovimentacaoDAO.registrar()
    ↓
setAutoCommit(false) - Inicia transação
    ↓
SE SAIDA:
  Valida se quantidade disponível >= quantidade movimentada
  SE não:
    Exibe erro
    rollback()
    Retorna
    ↓
Insere em movimentacao
    ↓
SE ENTRADA:
  UPDATE produto SET quantidade = quantidade + qntd_movimentada
SENAO (SAIDA):
  UPDATE produto SET quantidade = quantidade - qntd_movimentada
    ↓
commit() - Confirma transação
    ↓
Exibe sucesso para usuário
```

---

## 📈 Diagrama de Casos de Uso - Relatórios

```
                    ┌──────────────────┐
                    │  Usuário Sistema │
                    └────────┬─────────┘
                             │
                    ┌────────┴─────────┐
                    │                  │
            ┌───────▼────────┐  ┌──────▼───────────┐
            │  Visualizar    │  │ Gerar Relatório │
            │  Relatórios    │  │                  │
            └───────┬────────┘  └──────┬───────────┘
                    │                  │
        ┌───────────┼──────────────────┼──────────────┐
        │           │                  │              │
    ┌───▼──┐   ┌───▼──┐   ┌───────┐ ┌─▼──┐   ┌──────▼──┐
    │Lista │   │Balanço│  │Produtos│ │Prod│   │Produtos│
    │Preço │   │Financ │  │Abaixo  │ │Por │   │Mais    │
    │      │   │eiro   │  │Mínimo  │ │Cat │   │Movimen │
    │      │   │       │  │        │ │    │   │tados   │
    └──────┘   └───────┘  └────────┘ └────┘   └────────┘
```

---

## 🗂️ Mapeamento de Classes para Camadas

```
CAMADA DE APRESENTAÇÃO (visao/)
│
├─ FrmMenuPrincipal
│  └─ [Não usa DAO diretamente - apenas abre outras telas]
│
├─ FrmCadastroCategoria
│  └─ CategoriaDAO
│
├─ FrmCadastroProduto
│  └─ ProdutoDAO + CategoriaDAO (para combo de categorias)
│
├─ FrmCadastroMovimentacao
│  └─ MovimentacaoDAO + ProdutoDAO (para combo de produtos)
│
├─ FrmGerenciaCategoria
│  └─ CategoriaDAO
│
├─ FrmGerenciaProduto
│  └─ ProdutoDAO
│
├─ FrmGerenciaMovimentacao
│  └─ MovimentacaoDAO
│
└─ FrmRelatorios ✨ (NOVO!)
   └─ ProdutoDAO + CategoriaDAO + MovimentacaoDAO


CAMADA DE LÓGICA (dao/)
│
├─ ProdutoDAO
│  └─ Conexao
│
├─ CategoriaDAO
│  └─ Conexao
│
└─ MovimentacaoDAO
   └─ Conexao


CAMADA DE MODELO (modelo/)
│
├─ Produto
├─ Categoria
└─ Movimentacao


CAMADA DE UTILITÁRIO (db/)
│
├─ Conexao [Gerencia conexão com MySQL]
└─ ConexaoMySQL [Possível interface ou classe auxiliar]


INFRAESTRUTURA (enums/)
│
├─ Tamanhos {PEQUENO, MEDIO, GRANDE}
└─ Embalagens {LATA, VIDRO, PLASTICO}


PONTO DE ENTRADA (principal/)
│
└─ Main
   └─ FrmMenuPrincipal
```

---

## 🔐 Segurança e Validações

```
Entrada de Dados
    ↓
├─ Validação GUI (campos obrigatórios)
├─ Validação de Tipo (int, double, etc)
├─ Validação de Negócio (quantidade > 0)
    ↓
DAO
    ↓
├─ Try-Catch para SQL
├─ Prepared Statements (previne SQL Injection)
├─ Validação de Transação (rollback em erro)
├─ Foreign Keys (BD valida integridade)
    ↓
Banco de Dados
    ↓
├─ Constraints (NOT NULL, CHECK)
├─ Índices (performance)
├─ Foreign Keys (integridade referencial)
```

