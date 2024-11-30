# Projeto de Conexão com Banco de Dados - Teste de Caixa Branca

Este projeto tem como objetivo verificar e testar um código de conexão com banco de dados utilizando o padrão de teste **caixa branca**. O código foi analisado em busca de possíveis **erros** e **melhorias**.

## 💻 Descrição do Projeto

O código fornecido é responsável por realizar a conexão com um banco de dados MySQL e verificar as credenciais de um usuário (login e senha). Ele faz isso por meio de uma consulta SQL e exibe o nome do usuário caso as credenciais sejam válidas.

---

## 🛠️ **Erros Identificados e Melhorias Sugeridas**

Aqui estão os erros detectados durante o **Teste Estático** e as **melhorias** propostas:

### 1. **Driver MySQL Desatualizado**

- **Erro Identificado:**
  Na linha onde o driver de conexão é carregado:
  ```java
  Class.forName("com.mysql.Driver.Manager").newInstance();
O driver utilizado está incorreto e não é mais suportado nas versões recentes do MySQL.

- **Correção Sugerida:**
  Substituir pela versão correta do driver para MySQL:
  ```java
  Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

### 2. **Falta de Tratamento Adequado de Exceções**

- **Erro Identificado:**
  No bloco try-catch, quando ocorre uma exceção, o código não realiza nenhum tratamento:
  ```java
  catch (Exception e) {
    // Nenhum tratamento ou log do erro
  }
  
- **Correção Sugerida:**
  Adicionar um log ou printStackTrace para visualizar os erros durante a execução. Isso facilita o debug do código:
  ```java
  catch (Exception e) {
    e.printStackTrace(); // ou log para registrar o erro
  }

### 3. **Risco de Injeção de SQL**

- **Erro Identificado:**
  A consulta SQL é montada concatenando diretamente as entradas do usuário (login e senha):
  ```java
  sql += "select nome from usuarios where login = '" + login + "' and senha = '" + senha + "';";
Essa prática é insegura e vulnerável a injeção de SQL, um ataque que pode comprometer a segurança do banco de dados.

- **Correção Sugerida:**
  Utilizar PreparedStatements, que previnem injeção de SQL, parametrizando as consultas:
  ```java
  String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";
  PreparedStatement ps = conn.prepareStatement(sql);
  ps.setString(1, login);
  ps.setString(2, senha);

### 4. **Falta de Fechamento da Conexão com o Banco**

- **Erro Identificado:**
  O código não fecha a conexão com o banco de dados e outros recursos como Statement e ResultSet, o que pode causar vazamento de recursos:
  ```java
  // Nenhum fechamento da conexão ou dos recursos

- **Correção Sugerida:**
  Adicionar um bloco finally ou utilizar try-with-resources para garantir o fechamento dos recursos após seu uso:
  ```java
  try (Connection conn = DriverManager.getConnection(url);
     PreparedStatement ps = conn.prepareStatement(sql)) {
     // código aqui
  } catch (Exception e) {
      e.printStackTrace();
  }

### 5. **Credenciais Hardcoded no Código**

- **Erro Identificado:**
  As credenciais de acesso ao banco de dados estão hardcoded no código:
  ```java
  String url = "jdbc:mysql:localhost/connectiondb?user=admin&password=master";

- **Correção Sugerida:**
  As credenciais devem ser armazenadas em um arquivo de configuração externo ou em variáveis de ambiente para aumentar a segurança e facilitar a manutenção:
  ```java
  // Exemplo usando variáveis de ambiente
  String user = System.getenv("DB_USER");
  String password = System.getenv("DB_PASSWORD");
  String url = "jdbc:mysql://localhost/connectiondb?user=" + user + "&password=" + password;

---

## 📋 **Conclusão

Este projeto demonstrou a importância de seguir boas práticas de programação, especialmente no que diz respeito à segurança e ao manuseio de exceções. As melhorias propostas visam tornar o código mais seguro, robusto e preparado para cenários reais de uso em produção.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/pedroantunes1310/Testes-Caixa-Branca).
