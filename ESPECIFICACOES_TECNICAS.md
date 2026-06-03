# Especificações Técnicas - Sistema de Controle de Estoque

## 1. IMPLEMENTAÇÃO CRUD PRODUTO

### Entidade (modelo/Produto.java)
- **Atributos:**
  - `id`: int - Identificador único
  - `id_categoria`: int - Referência à categoria
  - `nome_categoria`: String - Nome da categoria (para exibição)
  - `nome`: String - Nome do produto
  - `quantidade`: int - Quantidade em estoque
  - `unidade`: String - Unidade de medida (kg, L, Un, etc)
  - `preco`: double - Preço unitário
  - `qntdMin`: int - Quantidade mínima em estoque
  - `qntdMax`: int - Quantidade máxima em estoque

- **Construtores:**
  - `Produto()` - Vazio
  - `Produto(id, id_categoria, nome, quantidade, preco, qntdMin, qntdMax)` - Básico
  - `Produto(id, id_categoria, nome_categoria, nome, quantidade, unidade, preco, qntdMin, qntdMax)` - Completo

- **Métodos:** Getters e setters para todos os atributos

### Data Access Object (dao/ProdutoDAO.java)
- `cadastrar(Produto)` - Insere novo produto
- `listar()` - Retorna lista ordenada alfabeticamente
- `buscarPorId(int)` - Busca produto por ID
- `atualizar(Produto)` - Atualiza informações do produto
- `excluir(int)` - Remove produto
- `listarAbaixoDoMinimo()` - Relatório de produtos com estoque abaixo do mínimo
- `listarBalancoFinanceiro()` - Balanço com valor total (quantidade × preço)
- `listarPrecos()` - Lista de preços com categoria e unidade
- `getTotalEstoque()` - Calcula valor total do estoque

### Visualização (visao/FrmCadastroProduto.java e FrmGerenciaProduto.java)
- Interface para cadastro de novos produtos
- Interface para gerenciar (editar/excluir) produtos existentes
- Validação de campos obrigatórios
- Integração com DAOs

---

## 2. IMPLEMENTAÇÃO CRUD CATEGORIA

### Entidade (modelo/Categoria.java)
- **Atributos:**
  - `idCategoria`: int - Identificador único
  - `nome`: String - Nome da categoria
  - `tamanho`: Tamanhos (enum) - PEQUENO, MEDIO, GRANDE
  - `embalagem`: Embalagens (enum) - LATA, VIDRO, PLASTICO

### Data Access Object (dao/CategoriaDAO.java)
- `cadastrar(Categoria)` - Insere nova categoria
- `listar()` - Retorna lista ordenada alfabeticamente
- `buscarPorId(int)` - Busca categoria por ID
- `atualizar(Categoria)` - Atualiza categoria
- `excluir(int)` - Remove categoria
- `listarQuantidadeProdutosPorCategoria()` - Relatório de quantidade de produtos

### Visualização (visao/FrmCadastroCategoria.java e FrmGerenciaCategoria.java)
- Interface para cadastro de categorias
- Seleção de tamanho (combo box)
- Seleção de embalagem (combo box)
- Gerenciamento de categorias

---

## 3. IMPLEMENTAÇÃO MOVIMENTAÇÃO (ENTRADA/SAÍDA)

### Entidade (modelo/Movimentacao.java)
- **Atributos:**
  - `id`: int - Identificador único
  - `id_produto`: int - Referência ao produto
  - `nomeProduto`: String - Nome do produto (para exibição)
  - `dataMovimentacao`: String - Data (formato: yyyy-MM-dd)
  - `qntdMovimentada`: int - Quantidade movimentada
  - `tipoMovimentacao`: String - ENTRADA ou SAIDA

### Data Access Object (dao/MovimentacaoDAO.java)
- `registrar(Movimentacao)` - Registra movimentação e atualiza estoque
- `listar()` - Retorna histórico de movimentações
- `listarPorProduto(int)` - Histórico de movimentações de um produto
- `produtoMaisEntradaSaida()` - Retorna produtos com mais entrada e saída

**Transações:**
- Usa `setAutoCommit(false)` para garantir integridade
- Valida quantidade antes de saída
- Realiza rollback em caso de erro

### Visualização (visao/FrmCadastroMovimentacao.java e FrmGerenciaMovimentacao.java)
- Interface para registrar entrada/saída
- Seleção de produto (combo box)
- Seleção de tipo de movimentação
- Data em formato apropriado
- Histórico de movimentações

---

## 4. IMPLEMENTAÇÃO DE RELATÓRIOS

### Tela de Relatórios (visao/FrmRelatorios.java)

#### Relatório 1: Lista de Preços
- **Colunas:** Produto | Preço Unitário | Unidade | Categoria
- **Ordem:** Alfabética por produto
- **Método DAO:** `ProdutoDAO.listarPrecos()`

#### Relatório 2: Balanço Físico/Financeiro
- **Colunas:** Produto | Unidade | Quantidade | Preço Unitário | Total por Produto
- **Ordem:** Alfabética por produto
- **Rodapé:** Total do estoque (somatório de todos os produtos)
- **Método DAO:** `ProdutoDAO.listarBalancoFinanceiro()`

#### Relatório 3: Produtos Abaixo do Mínimo
- **Colunas:** Produto | Quantidade Mínima | Quantidade em Estoque | Categoria
- **Filtro:** Apenas produtos com estoque < quantidade mínima
- **Método DAO:** `ProdutoDAO.listarAbaixoDoMinimo()`

#### Relatório 4: Quantidade de Produtos por Categoria
- **Colunas:** Categoria | Quantidade de Produtos
- **Ordem:** Alfabética por categoria
- **Método DAO:** `CategoriaDAO.listarQuantidadeProdutosPorCategoria()`

#### Relatório 5: Produtos Mais Movimentados
- **Tipo:** Entrada/Saída
- **Informação:** Produto com maior quantidade total de entrada e produto com maior quantidade total de saída
- **Método DAO:** `MovimentacaoDAO.produtoMaisEntradaSaida()`

---

## 5. BANCO DE DADOS

### Tabela: categoria
```sql
CREATE TABLE categoria (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tamanho VARCHAR(20) NOT NULL,
    embalagem VARCHAR(20) NOT NULL
);
```

### Tabela: produto
```sql
CREATE TABLE produto (
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
```

### Tabela: movimentacao
```sql
CREATE TABLE movimentacao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_produto INT NOT NULL,
    data_movimentacao DATE NOT NULL,
    qntd_movimentada INT NOT NULL,
    tipo_movimentacao VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id)
);
```

---

## 6. PADRÕES DE CÓDIGO

### Nomenclatura
- **Pacotes:** Português Brasil, minúsculas
- **Classes:** Português Brasil, CamelCase (ex: `FrmCadastroProduto`)
- **Métodos:** Português Brasil, camelCase (ex: `gerarRelatorio()`)
- **Atributos:** Português Brasil, camelCase (ex: `quantidadeMinima`)
- **Constantes:** MAIUSCULAS com underscore (ex: `TAMANHO_MAXIMO`)

### Organização
- Uma classe por arquivo
- Getters e setters agrupados
- Métodos de negócio antes de métodos auxiliares
- Comentários em pontos críticos

### Tabulação
- 4 espaços ou 1 tab (configurado no IDE)
- Coerência em todo o projeto

### Padrões de Design
- **DAO Pattern:** Separação entre lógica e acesso a dados
- **MVC Pattern:** Model (classe do modelo) + View (GUI) + Controller (DAO)

---

## 7. VALIDAÇÕES IMPLEMENTADAS

1. ✅ **Quantidade em Estoque:** Não permite venda com quantidade insuficiente
2. ✅ **Transações:** Usa rollback em caso de erro
3. ✅ **Campos Obrigatórios:** Valida preenchimento na GUI
4. ✅ **Integridade Referencial:** Foreign keys no banco de dados
5. ✅ **Datas:** Formato padronizado

---

## 8. TRATAMENTO DE ERROS

- Todos os métodos DAO usam try-catch
- Mensagens de erro descritivas em `JOptionPane`
- Log de erros em console quando apropriado

---

## 9. DEPENDÊNCIAS

- **mysql-connector-j:8.0.33** - Driver JDBC para MySQL
- **Java 25+** - Versão mínima do JDK
- **Swing** - Framework para GUI (incluso no JDK)

