# Projeto de Conexão com Banco de Dados - Teste de Caixa Branca (Etapa 3)

## 🚀 Etapa 3: Análise de Caixa Branca

Nesta etapa, realizamos a análise de **Caixa Branca** do código do projeto, que incluiu a criação de um **grafo de fluxo de controle**, o cálculo da **complexidade ciclômica** e a definição da **base de caminhos** independentes.

---

## 📊 Grafo de Fluxo de Controle

O grafo de fluxo abaixo representa o caminho de execução do código:

![Grafo de Fluxo de Controle](./docs/fluxo_de_controle.png)

**Explicação do Grafo:**
1. **Ponto de Entrada**: A execução do código começa com o método `conectarBD()`, onde tentamos carregar o driver MySQL.
2. **Ponto de Decisão**: Se o driver MySQL for carregado corretamente, a execução continua com a tentativa de conexão ao banco de dados. Se falhar, ocorre o tratamento de exceção.
3. **Verificação do Usuário**: O método `verificarUsuario()` executa uma consulta SQL para verificar se o login e a senha são válidos, com pontos de decisão que verificam se o usuário foi encontrado ou se houve uma falha na execução da query.

---

## 🧮 Complexidade Ciclômica

A **complexidade ciclômica** mede o número de caminhos linearmente independentes dentro do código. Para calcular a complexidade ciclômica, usamos a fórmula:

Complexidade Ciclômica (M) = E - N + 2P

Onde:
- **E** = Número de arestas (transições possíveis no grafo de fluxo)
- **N** = Número de nodos (pontos de controle no código)
- **P** = Número de componentes conectados (normalmente 1, já que temos um único fluxo)

**Cálculo:**
- **Nodos (N)**: 19
- **Arestas (E)**: 21 (conforme o fluxo de controle gerado)
- **Pontos de entrada/saída (P)**: 1

**Resultado:**

M = 21 - 19 + 2*1 M = 4

A complexidade ciclômica do código é **4**.

---

## 🛤️ Base de Caminhos

A **Base de Caminhos** representa os caminhos linearmente independentes no código. Baseado no grafo de fluxo, temos os seguintes caminhos:

### Caminho 1: Fluxo sem falha
1. Carregar driver
2. Conectar ao banco de dados
3. Retornar a conexão
4. Montar a query SQL
5. Criar `Statement`
6. Executar a query
7. `ResultSet` encontrado, atribuir `result = true`
8. Retornar `result`

### Caminho 2: Driver falha ao carregar
1. Carregar driver
2. Falha no driver, tratamento de exceção
3. Retornar `null`

### Caminho 3: Conexão falha
1. Carregar driver
2. Conectar ao banco de dados
3. Falha ao conectar, tratamento de exceção
4. Retornar `null`

### Caminho 4: `ResultSet` não encontrado
1. Carregar driver
2. Conectar ao banco de dados
3. Retornar a conexão
4. Montar a query SQL
5. Criar `Statement`
6. Executar a query
7. `ResultSet` não encontrado, atribuir `result = false`
8. Retornar `result`

---
