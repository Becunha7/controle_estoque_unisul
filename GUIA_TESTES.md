# Guia de Testes - Sistema de Controle de Estoque

## Pré-Requisitos para Testes
1. ✅ MySQL em execução
2. ✅ Banco de dados criado (execute banco_dados.sql)
3. ✅ Dados de exemplo carregados (execute dados_exemplo.sql)
4. ✅ Aplicação compilada e em execução

---

## 1️⃣ Testes de Cadastro

### Teste 1.1: Cadastrar Categoria
**Passos:**
1. Clique em "Cadastrar Categoria" no menu principal
2. Preencha:
   - Nome: "Eletrônicos"
   - Tamanho: Selecione "Pequeno"
   - Embalagem: Selecione "Vidro"
3. Clique em "Salvar"

**Resultado Esperado:**
- ✅ Mensagem "Categoria salva com sucesso!"
- ✅ Categoria aparece em "Gerenciar Categoria"

---

### Teste 1.2: Cadastrar Produto
**Passos:**
1. Clique em "Cadastrar Produto"
2. Preencha:
   - Categoria: Selecione uma categoria existente
   - Nome: "Notebook Dell"
   - Quantidade: 10
   - Unidade: "Un"
   - Preço: 3500.00
   - Quantidade Mínima: 5
   - Quantidade Máxima: 20
3. Clique em "Salvar"

**Resultado Esperado:**
- ✅ Mensagem "Produto salvo com sucesso!"
- ✅ Produto aparece em "Gerenciar Produto"

---

### Teste 1.3: Cadastrar Movimentação
**Passos:**
1. Clique em "Cadastrar Movimentação"
2. Preencha:
   - Produto: Selecione um produto (ex: "Notebook Dell")
   - Tipo: Selecione "Entrada"
   - Data: 01/01/2025
   - Quantidade: 5
3. Clique em "Salvar"

**Resultado Esperado:**
- ✅ Mensagem "Movimentação registrada com sucesso!"
- ✅ Quantidade do produto aumenta em 5 unidades

---

## 2️⃣ Testes de Atualização

### Teste 2.1: Atualizar Categoria
**Passos:**
1. Clique em "Gerenciar Categoria"
2. Selecione uma categoria na tabela
3. Modifique os dados (ex: mudar tamanho para "Grande")
4. Clique em "Atualizar"

**Resultado Esperado:**
- ✅ Mensagem "Categoria atualizada!"
- ✅ Dados refletem a mudança na tabela

---

### Teste 2.2: Atualizar Produto
**Passos:**
1. Clique em "Gerenciar Produto"
2. Selecione um produto
3. Modifique o preço (ex: 2999.90)
4. Clique em "Atualizar"

**Resultado Esperado:**
- ✅ Mensagem "Produto atualizado com sucesso!"
- ✅ Novo preço aparece na tabela

---

## 3️⃣ Testes de Exclusão

### Teste 3.1: Excluir Categoria
**Passos:**
1. Clique em "Gerenciar Categoria"
2. Selecione uma categoria sem produtos
3. Clique em "Excluir"
4. Confirme a exclusão

**Resultado Esperado:**
- ✅ Mensagem "Categoria excluída!"
- ✅ Categoria desaparece da tabela

**Nota:** Não é possível excluir categoria que possui produtos (integridade referencial)

---

### Teste 3.2: Excluir Produto
**Passos:**
1. Clique em "Gerenciar Produto"
2. Selecione um produto
3. Clique em "Excluir"
4. Confirme

**Resultado Esperado:**
- ✅ Mensagem "Produto excluído com sucesso!"
- ✅ Produto desaparece da tabela

---

## 4️⃣ Testes de Relatórios 🎯

### Teste 4.1: Relatório - Lista de Preços
**Passos:**
1. Clique em "Relatórios"
2. Clique em "Lista de Preços"

**Resultado Esperado:**
- ✅ Tabela exibida com colunas:
  - Produto | Preço Unitário | Unidade | Categoria
- ✅ Produtos em ordem alfabética
- ✅ Preços formatados com "R$"

**Validação:**
- Todos os produtos cadastrados aparecem?
- Preços estão corretos?
- Ordem é alfabética?

---

### Teste 4.2: Relatório - Balanço Financeiro
**Passos:**
1. Na tela de Relatórios, clique em "Balanço Financeiro"

**Resultado Esperado:**
- ✅ Tabela com colunas:
  - Produto | Unidade | Quantidade | Preço Unitário | Total por Produto
- ✅ Última linha: "TOTAL DO ESTOQUE" com soma
- ✅ Pop-up exibindo valor total do estoque
- ✅ Valores formatados com "R$"

**Validação:**
- Total = Σ(Quantidade × Preço) para cada produto?
- Grande total está correto?

**Exemplo Cálculo:**
- Produto A: 10 un × R$ 50 = R$ 500
- Produto B: 5 un × R$ 100 = R$ 500
- **Total Estoque: R$ 1000**

---

### Teste 4.3: Relatório - Produtos Abaixo do Mínimo
**Preparação:**
1. Editar um produto para ter quantidade menor que mínimo
   - Quantidade: 2
   - Quantidade Mínima: 5

**Passos:**
1. Clique em "Produtos Abaixo do Mínimo"

**Resultado Esperado:**
- ✅ Tabela exibida apenas com produtos abaixo do mínimo
- ✅ Colunas: Produto | Qntd Mínima | Qntd em Estoque | Categoria
- ✅ Se nenhum produto abaixo do mínimo: mensagem "Nenhum produto abaixo da quantidade mínima."

**Validação:**
- Apenas aparecem produtos com estoque < mínimo?

---

### Teste 4.4: Relatório - Quantidade de Produtos por Categoria
**Passos:**
1. Clique em "Produtos por Categoria"

**Resultado Esperado:**
- ✅ Tabela com colunas: Categoria | Quantidade de Produtos
- ✅ Todas as categorias listadas em ordem alfabética
- ✅ Contagem correta de produtos por categoria

**Validação:**
- Contagem está correta?
- Categorias sem produtos mostram 0?

**Exemplo:**
- Eletrônicos: 3 produtos
- Alimentos: 5 produtos
- Limpeza: 2 produtos

---

### Teste 4.5: Relatório - Produtos Mais Movimentados
**Preparação:**
1. Registrar várias movimentações para diferentes produtos
   - Produto A: 5 entradas (20 un) + 2 saídas (8 un)
   - Produto B: 2 entradas (15 un) + 5 saídas (25 un)

**Passos:**
1. Clique em "Produtos Mais Movimentados"

**Resultado Esperado:**
- ✅ Tabela com 2 linhas:
  - Tipo: "Mais Entrada" → Produto A (20 unidades)
  - Tipo: "Mais Saída" → Produto B (25 unidades)
- ✅ Se sem dados: "Sem dados"

---

## 5️⃣ Testes de Validação de Negócio

### Teste 5.1: Saída com Estoque Insuficiente
**Preparação:**
- Produto tem 10 unidades em estoque

**Passos:**
1. Clique em "Cadastrar Movimentação"
2. Selecione o produto
3. Tipo: "Saída"
4. Quantidade: 15 (maior que disponível)
5. Clique "Salvar"

**Resultado Esperado:**
- ❌ Mensagem de erro: "Quantidade insuficiente em estoque para essa saída."
- ❌ Quantidade do produto NÃO muda

---

### Teste 5.2: Entrada Aumenta Estoque Corretamente
**Preparação:**
- Produto tem 10 unidades

**Passos:**
1. Registre uma ENTRADA de 5 unidades
2. Verifique "Gerenciar Produto"

**Resultado Esperado:**
- ✅ Quantidade = 10 + 5 = **15 unidades**

---

### Teste 5.3: Saída Diminui Estoque Corretamente
**Preparação:**
- Produto tem 15 unidades

**Passos:**
1. Registre uma SAÍDA de 3 unidades
2. Verifique "Gerenciar Produto"

**Resultado Esperado:**
- ✅ Quantidade = 15 - 3 = **12 unidades**

---

## 6️⃣ Testes de Integridade de Dados

### Teste 6.1: Integridade Referencial
**Passos:**
1. Tente excluir uma categoria que possui produtos

**Resultado Esperado:**
- ❌ Mensagem de erro sobre restrição
- ❌ Categoria NÃO é excluída

---

### Teste 6.2: Dados Persistidos no BD
**Passos:**
1. Feche a aplicação
2. Abra novamente
3. Verifique "Gerenciar Produto"

**Resultado Esperado:**
- ✅ Todos os produtos cadastrados ainda aparecem
- ✅ Quantidades correspondem às últimas operações

---

## 7️⃣ Testes de Interface (UI)

### Teste 7.1: Navegação entre Telas
**Passos:**
1. Do menu principal, abra cada tela (Cadastro, Gerência, Relatórios)
2. Feche com o botão X ou "Fechar"
3. Volte ao menu principal

**Resultado Esperado:**
- ✅ Todas as telas abrem e fecham corretamente
- ✅ Menu principal permanece funcional

---

### Teste 7.2: Formatação de Moeda nos Relatórios
**Passos:**
1. Gere "Lista de Preços"
2. Observe a coluna "Preço Unitário"
3. Gere "Balanço Financeiro"
4. Observe colunas monetárias

**Resultado Esperado:**
- ✅ Valores exibem como "R$ 1234.50"
- ✅ Não aparecem valores brutos (1234.5)

---

## 📋 Checklist Final de Testes

```
CRUD OPERATIONS
├─ [_] Cadastrar Categoria
├─ [_] Cadastrar Produto
├─ [_] Cadastrar Movimentação
├─ [_] Atualizar Categoria
├─ [_] Atualizar Produto
├─ [_] Excluir Categoria
└─ [_] Excluir Produto

RELATÓRIOS
├─ [_] Lista de Preços
├─ [_] Balanço Financeiro
├─ [_] Produtos Abaixo do Mínimo
├─ [_] Quantidade de Produtos por Categoria
└─ [_] Produtos Mais Movimentados

VALIDAÇÕES
├─ [_] Saída com estoque insuficiente
├─ [_] Entrada aumenta estoque
├─ [_] Saída diminui estoque
├─ [_] Integridade referencial
└─ [_] Persistência de dados

INTERFACE
├─ [_] Navegação entre telas
├─ [_] Formatação de moeda
├─ [_] Mensagens de erro/sucesso
└─ [_] Ordem alfabética nos relatórios
```

---

## 🐛 Possíveis Erros e Soluções

| Erro | Causa | Solução |
|------|-------|---------|
| "Connection refused" | MySQL não está rodando | Inicie o MySQL |
| "Table doesn't exist" | BD não foi criado | Execute banco_dados.sql |
| "ClassNotFoundException" | Driver MySQL ausente | Recompile o projeto |
| Nenhum dado nos relatórios | BD vazio | Execute dados_exemplo.sql |
| Valores de preço incorretos | Formatação | Verifique DecimalFormat |

---

## 📝 Anotações de Teste

Use este espaço para anotar problemas encontrados:

```
Teste Data: ____________
Versão do Java: ________

Problemas Encontrados:
1. _______________________________________
2. _______________________________________
3. _______________________________________

Soluções Aplicadas:
1. _______________________________________
2. _______________________________________
3. _______________________________________

Status Final: [_] PASSOU  [_] FALHOU
```

