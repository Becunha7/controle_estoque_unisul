# Guia de Execução - Sistema de Controle de Estoque

## Pré-requisitos

1. **Java JDK 25+** instalado
2. **MySQL** instalado e em execução
3. **Maven** instalado (opcional, para compilação)
4. **NetBeans** ou outro IDE Java (recomendado)

## Configuração do Banco de Dados

### 1. Criar o banco de dados
Abra o MySQL e execute o script SQL:

```bash
mysql -u root -p < src/main/resources/banco_dados.sql
```

Ou copie e cole o conteúdo do arquivo `banco_dados.sql` no MySQL Workbench e execute.

### 2. Verificar credenciais de conexão
O projeto está configurado para conectar em:
- **Host:** localhost
- **Port:** 3306
- **Database:** controle_estoque_unisul
- **User:** root
- **Password:** root

Se suas credenciais forem diferentes, edite o arquivo:
`src/main/java/db/Conexao.java`

## Como Executar

### Opção 1: NetBeans (Recomendado)
1. Abra o NetBeans
2. Selecione "File" > "Open Project"
3. Navegue até a pasta do projeto e abra
4. Clique em "Clean and Build"
5. Clique em "Run Main Project" ou pressione F6

### Opção 2: Compilação Manual
```bash
cd src/main/java
javac -cp ".:path/to/mysql-connector-j-8.0.33.jar" db/Conexao.java
javac -cp ".:path/to/mysql-connector-j-8.0.33.jar" modelo/*.java
javac -cp ".:path/to/mysql-connector-j-8.0.33.jar" dao/*.java
javac -cp ".:path/to/mysql-connector-j-8.0.33.jar" visao/*.java
javac -cp ".:path/to/mysql-connector-j-8.0.33.jar" principal/Main.java

java -cp ".:path/to/mysql-connector-j-8.0.33.jar" principal.Main
```

### Opção 3: Maven
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="principal.Main"
```

## Funcionalidades Implementadas

### 1. CRUD Completo
- ✅ **Categorias:** Cadastrar, Listar, Atualizar, Excluir
- ✅ **Produtos:** Cadastrar, Listar, Atualizar, Excluir
- ✅ **Movimentações:** Registrar Entrada/Saída

### 2. Relatórios
- ✅ **Lista de Preços:** Produtos em ordem alfabética com preço, unidade e categoria
- ✅ **Balanço Físico/Financeiro:** Quantidade em estoque e valor total (unitário × quantidade)
- ✅ **Produtos Abaixo do Mínimo:** Produtos com quantidade abaixo do estoque mínimo
- ✅ **Quantidade de Produtos por Categoria:** Estatísticas por categoria
- ✅ **Produtos Mais Movimentados:** Produto com mais entrada e mais saída

### 3. Validações
- ✅ Controle de quantidade em estoque
- ✅ Validação de entrada/saída de produtos
- ✅ Prevenção de venda com estoque insuficiente

## Estrutura do Projeto

```
src/main/java/
├── db/               → Conexão com banco de dados
├── dao/              → Data Access Objects (acesso aos dados)
├── modelo/           → Classes de modelo (Produto, Categoria, Movimentacao)
├── enums/            → Enumerações (Tamanhos, Embalagens)
├── visao/            → Interfaces gráficas (Frames)
└── principal/        → Classe principal (Main)
```

## Informações de Tabelas

### Categoria
- **id:** ID único
- **nome:** Nome da categoria
- **tamanho:** PEQUENO, MEDIO, GRANDE
- **embalagem:** LATA, VIDRO, PLASTICO

### Produto
- **id:** ID único
- **id_categoria:** Referência à categoria
- **nome:** Nome do produto
- **quantidade:** Quantidade em estoque
- **unidade:** Unidade de medida
- **preco:** Preço unitário
- **qntd_min:** Quantidade mínima
- **qntd_max:** Quantidade máxima

### Movimentacao
- **id:** ID único
- **id_produto:** Referência ao produto
- **data_movimentacao:** Data da movimentação
- **qntd_movimentada:** Quantidade movimentada
- **tipo_movimentacao:** ENTRADA ou SAIDA

## Padrões de Código

- **Nomenclatura:** Português Brasil
- **Pacotes:** Organizados por funcionalidade
- **Tabulação:** 4 espaços ou 1 tab
- **Comentários:** Em português, descrevendo funcionalidades importantes

## Dúvidas ou Problemas?

1. Verifique se o MySQL está em execução
2. Verifique se o banco de dados foi criado corretamente
3. Verifique as credenciais de conexão em `db/Conexao.java`
4. Verifique se todas as dependências do Maven estão instaladas

