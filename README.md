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

## 📁 Estrutura do Projeto

```
controle_estoque_unisul/
├── src/main/java/
│   ├── db/                          # Conexão com banco de dados
│   │   ├── Conexao.java
│   │   └── ConexaoMySQL.java
│   ├── dao/                         # Data Access Objects
│   │   ├── ProdutoDAO.java
│   │   ├── CategoriaDAO.java
│   │   └── MovimentacaoDAO.java
│   ├── modelo/                      # Modelos de dados
│   │   ├── Produto.java
│   │   ├── Categoria.java
│   │   └── Movimentacao.java
│   ├── enums/                       # Enumerações
│   │   ├── Tamanhos.java
│   │   └── Embalagens.java
│   ├── visao/                       # Interfaces gráficas
│   │   ├── FrmMenuPrincipal.java
│   │   ├── FrmCadastroCategoria.java
│   │   ├── FrmCadastroProduto.java
│   │   ├── FrmCadastroMovimentacao.java
│   │   ├── FrmGerenciaCategoria.java
│   │   ├── FrmGerenciaProduto.java
│   │   ├── FrmGerenciaMovimentacao.java
│   │   └── FrmRelatorios.java
│   └── principal/                   # Ponto de entrada
│       └── Main.java
├── src/main/resources/
│   ├── banco_dados.sql              # Script de criação do BD
│   └── dados_exemplo.sql            # Dados de teste
├── pom.xml                          # Configuração Maven
├── README.md                        # Este arquivo
├── GUIA_EXECUCAO.md                 # Guia de como executar
└── ESPECIFICACOES_TECNICAS.md       # Documentação técnica
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 25+ instalado
- MySQL 8.0+ instalado e rodando
- Maven instalado (opcional)

### Passo 1: Configurar Banco de Dados

```bash
# Abra o MySQL
mysql -u root -p

# Execute o script de criação
source src/main/resources/banco_dados.sql

# (Opcional) Carregue dados de exemplo
source src/main/resources/dados_exemplo.sql
```

### Passo 2: Compilar e Executar

**Opção A: NetBeans**
1. Abra o projeto em NetBeans
2. Clique em "Clean and Build"
3. Clique em "Run Main Project" (F6)

**Opção B: Maven**
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="principal.Main"
```

**Opção C: Compilação Manual**
```bash
javac -cp ".:mysql-connector-j-8.0.33.jar" src/main/java/**/*.java
java -cp ".:mysql-connector-j-8.0.33.jar" principal.Main
```

---

## 📝 Informações das Entidades

### Produto
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | int | Identificador único |
| id_categoria | int | Referência à categoria |
| nome | varchar | Nome do produto |
| quantidade | int | Quantidade em estoque |
| unidade | varchar | kg, L, Un, etc. |
| preco | decimal | Preço unitário |
| qntd_min | int | Quantidade mínima |
| qntd_max | int | Quantidade máxima |

### Categoria
| Campo | Tipo | Valores |
|-------|------|--------|
| id | int | Identificador único |
| nome | varchar | Nome da categoria |
| tamanho | varchar | PEQUENO, MEDIO, GRANDE |
| embalagem | varchar | LATA, VIDRO, PLASTICO |

### Movimentação
| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | int | Identificador único |
| id_produto | int | Referência ao produto |
| data_movimentacao | date | Data da movimentação |
| qntd_movimentada | int | Quantidade |
| tipo_movimentacao | varchar | ENTRADA ou SAIDA |

---

## 🔍 Padrões de Código

### Nomenclatura
- **Pacotes:** português brasil, minúsculas
- **Classes:** português brasil, CamelCase
- **Métodos:** português brasil, camelCase
- **Atributos:** português brasil, camelCase

### Organização
- Separação clara entre camadas (DAO, Modelo, Visão)
- Comentários em pontos críticos
- Tratamento de exceções consistente
- Validações de entrada

---

## ⚙️ Validações Implementadas

✅ Quantidade em estoque: Valida se há quantidade suficiente para saída
✅ Transações: Garante integridade em operações de entrada/saída
✅ Campos obrigatórios: Valida preenchimento na GUI
✅ Integridade referencial: Foreign keys no banco de dados
✅ Datas: Formato padronizado (yyyy-MM-dd)

---

## 🐛 Troubleshooting

### Erro: "Connection refused"
- Verifique se MySQL está em execução
- Verifique credenciais em `db/Conexao.java`

### Erro: "Table doesn't exist"
- Execute o script `banco_dados.sql`
- Verifique nome do banco de dados

### Erro: "ClassNotFound: MySQLDriver"
- Verifique se a dependência MySQL está no pom.xml
- Limpe e recompile o projeto

---

## 📖 Documentação

- **GUIA_EXECUCAO.md** - Como compilar e executar o projeto
- **ESPECIFICACOES_TECNICAS.md** - Detalhes técnicos de implementação

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos na Universidade do Sul de Santa Catarina (UNISUL).

---

## 📧 Contato

Para dúvidas ou sugestões, entre em contato com os desenvolvedores:
- Bernardo Soares da Cunha
- Lucas da Silveira Gentil


