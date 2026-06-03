# Quick Start - Início Rápido

## ⚡ 5 Minutos para Rodar o Sistema

### Pré-Requisitos (Instale Antes)
```
1. Java JDK 25+ (https://www.oracle.com/java/technologies/downloads/)
2. MySQL 8.0+ (https://www.mysql.com/downloads/)
3. NetBeans (https://netbeans.apache.org/) - Recomendado
```

---

## 🚀 OPÇÃO 1: NetBeans (Mais Fácil)

### Passo 1: Abrir Projeto
```
1. Abra NetBeans
2. File > Open Project
3. Navegue até: C:\Users\genti\OneDrive\Music\controle_estoque_unisul
4. Clique "Open Project"
```

### Passo 2: Preparar Banco de Dados
```
1. Abra MySQL (cmd ou workbench)
2. Execute:
   mysql -u root -p
   [Digite senha: root]
   source C:\Users\genti\OneDrive\Music\controle_estoque_unisul\src\main\resources\banco_dados.sql
```

### Passo 3: Rodar
```
1. NetBeans > Right-click no projeto
2. Clean and Build
3. F6 ou Run Main Project
```

**Pronto! ✅ Sistema aberto**

---

## 🚀 OPÇÃO 2: Linha de Comando

### Passo 1: Preparar BD
```cmd
cd C:\
mysql -u root -p
[Digite: root]
source C:\Users\genti\OneDrive\Music\controle_estoque_unisul\src\main\resources\banco_dados.sql
exit
```

### Passo 2: Compilar
```cmd
cd C:\Users\genti\OneDrive\Music\controle_estoque_unisul
mvn clean compile
mvn exec:java -Dexec.mainClass="principal.Main"
```

**Pronto! ✅ Sistema aberto**

---

## 🐞 Erros Comuns & Soluções

### ❌ "connection refused"
```
Solução: Certifique-se que MySQL está em execução
Windows: Services > MySQL80 > Start
```

### ❌ "Table already exists"
```
Solução: Banco de dados já foi criado
Continuar normalmente (é só um aviso)
```

### ❌ "ClassNotFoundException: MySQLDriver"
```
Solução: Recompile o projeto
NetBeans: Clean and Build
```

### ❌ "No projects found to display"
```
Solução: Projeto não está no local correto
Verifique o caminho: C:\Users\genti\OneDrive\Music\controle_estoque_unisul
```

---

## 📊 Primeira Execução - O Que Fazer

1. **Clique em "Cadastrar Categoria"**
   - Nome: "Eletrônicos"
   - Tamanho: "Pequeno"
   - Embalagem: "Vidro"
   - Salvar

2. **Clique em "Cadastrar Produto"**
   - Categoria: "Eletrônicos"
   - Nome: "Mouse"
   - Quantidade: 10
   - Unidade: "Un"
   - Preço: 50.00
   - Mínimo: 5
   - Máximo: 20
   - Salvar

3. **Clique em "Relatórios"**
   - Clique em "Lista de Preços"
   - Veja o mouse listado com preço

4. **Clique em "Balanço Financeiro"**
   - Veja valor total do mouse (10 × R$ 50 = R$ 500)

---

## 📚 Documentação Disponível

| Arquivo | Descrição |
|---------|-----------|
| **README.md** | Visão geral do projeto |
| **GUIA_EXECUCAO.md** | Como compilar/executar |
| **ESPECIFICACOES_TECNICAS.md** | Detalhes técnicos |
| **ARQUITETURA.md** | Estrutura do sistema |
| **GUIA_TESTES.md** | Plano de testes |
| **RESUMO_IMPLEMENTACAO.md** | O que foi feito |

---

## ⚙️ Verificação Final

### Checklist de Instalação
- [ ] Java 25+ instalado (`java -version`)
- [ ] MySQL em execução
- [ ] Banco de dados criado
- [ ] NetBeans aberto (ou cmd configurado)
- [ ] Projeto carregado
- [ ] Compilação sem erros
- [ ] Aplicação iniciando

### Se tudo OK:
```
✅ Menu Principal aparece
✅ Botões funcionam
✅ Telas abrem ao clicar
```

---

## 🎯 Próximos Passos

1. Explore cada tela (Cadastros, Gerência)
2. Veja os 5 relatórios funcionando
3. Leia a documentação para detalhes
4. Execute o GUIA_TESTES.md para teste completo

---

## 💡 Dicas

### Para Testar Relatórios Completos:
```sql
1. Execute dados_exemplo.sql também:
   source src/main/resources/dados_exemplo.sql

2. Isso carrega 4 categorias + 8 produtos + movimentações
3. Todos os relatórios terão dados reais
```

### Para Resetar o Banco:
```sql
1. DROP DATABASE controle_estoque_unisul;
2. Execute banco_dados.sql novamente
```

### Para Verificar Dados:
```sql
mysql -u root -p controle_estoque_unisul
SELECT * FROM categoria;
SELECT * FROM produto;
SELECT * FROM movimentacao;
```

---

## 📞 Suporte Rápido

| Problema | Onde Procurar |
|----------|--------------|
| Não compila | GUIA_EXECUCAO.md |
| BD não conecta | GUIA_EXECUCAO.md |
| Relatório vazio | GUIA_TESTES.md |
| Como funciona? | ESPECIFICACOES_TECNICAS.md |
| Arquitetura | ARQUITETURA.md |

---

## ✅ Status: Pronto para Usar!

O sistema está 100% funcional e pronto para uso.

**Divirta-se!** 🎉

