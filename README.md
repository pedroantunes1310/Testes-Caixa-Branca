# Projeto de Conexão com Banco de Dados - Etapa 4

## 🚀 Etapa 4: Correções no Código e Implementação da Documentação Javadoc

Nesta etapa, corrigimos o código para implementar boas práticas de programação, como o uso de **PreparedStatement** para evitar SQL Injection, o correto tratamento de exceções, e o fechamento adequado da conexão com o banco de dados utilizando **try-with-resources**. Além disso, foi implementada a **documentação Javadoc** para todas as classes e métodos, proporcionando uma visão clara das funcionalidades do código.

---

## 🛠️ Melhorias Implementadas no Código

### 1. **Uso de PreparedStatement**
- O código agora utiliza **PreparedStatement** para evitar **SQL Injection**, substituindo a concatenação de strings no método `verificarUsuario()`. Isso garante que os parâmetros da consulta SQL sejam passados de forma segura.

### 2. **Tratamento Adequado de Exceções**
- Corrigimos o tratamento de exceções utilizando **SQLException** e **ClassNotFoundException**, o que permite um melhor controle de erros durante a execução do código.

### 3. **Fechamento de Recursos (try-with-resources)**
- Implementamos o **try-with-resources** para garantir que a conexão com o banco de dados, os statements e os ResultSets sejam fechados automaticamente após o uso, evitando vazamentos de recursos.

### 4. **Uso de Variáveis de Ambiente para Credenciais**
- As credenciais do banco de dados agora são armazenadas em **variáveis de ambiente** (ex: `DB_USER` e `DB_PASSWORD`), aumentando a segurança e facilitando a configuração em diferentes ambientes.

---

## 📋 Código Corrigido

```java
package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe User que realiza a conexão com o banco de dados MySQL
 * e verifica as credenciais de login e senha.
 */
public class User {

    // URL de conexão, utilizando variáveis de ambiente para proteger as credenciais
    private static final String URL = "jdbc:mysql://localhost/connectiondb";
    private static final String USER = System.getenv("DB_USER");  // Variável de ambiente para o usuário
    private static final String PASSWORD = System.getenv("DB_PASSWORD");  // Variável de ambiente para a senha

    /**
     * Conecta ao banco de dados MySQL.
     * 
     * @return Um objeto {@link Connection} representando a conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro na conexão com o banco de dados.
     */
    public Connection conectarBD() throws SQLException {
        Connection conn;
        try {
            // Carregar o driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Lidar com erro de driver não encontrado
            throw new SQLException("Driver MySQL não encontrado.", e);
        }
        return conn;
    }

    /**
     * Nome do usuário após o login bem-sucedido.
     */
    public String nome = "";

    /**
     * Resultado da verificação de login.
     * Será {@code true} se o login for bem-sucedido, {@code false} caso contrário.
     */
    public boolean result = false;

    /**
     * Verifica se o login do usuário e a senha são válidos.
     * 
     * @param login O nome de usuário fornecido.
     * @param senha A senha fornecida.
     * @return {@code true} se o login e senha forem válidos, {@code false} caso contrário.
     * @throws SQLException Se ocorrer um erro ao consultar o banco de dados.
     */
    public boolean verificarUsuario(String login, String senha) throws SQLException {
        String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";
        
        // Usando try-with-resources para garantir o fechamento dos recursos (conexão, PreparedStatement e ResultSet)
        try (Connection conn = conectarBD();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            // Definir os parâmetros para a consulta SQL
            ps.setString(1, login);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Login válido, salvar o nome do usuário
                    result = true;
                    nome = rs.getString("nome");
                } else {
                    // Login ou senha inválidos
                    result = false;
                }
            }
        } catch (SQLException e) {
            // Tratamento de exceção no caso de erro de consulta ou conexão
            throw new SQLException("Erro ao verificar o usuário no banco de dados.", e);
        }
        return result;
    }
}


💻👾 Vamos criar o README.md da Etapa 4, focando nas correções e implementação da documentação Javadoc!

Na Etapa 4, fizemos correções no código original, aplicamos as melhores práticas para conexão JDBC e implementamos a documentação Javadoc para facilitar a compreensão e a manutenção do código. Agora, vamos montar o README.md descrevendo essas mudanças e a inclusão da documentação Javadoc.

📜 README.md para a Etapa 4:

# Projeto de Conexão com Banco de Dados - Etapa 4

## 🚀 Etapa 4: Correções no Código e Implementação da Documentação Javadoc

Nesta etapa, corrigimos o código para implementar boas práticas de programação, como o uso de **PreparedStatement** para evitar SQL Injection, o correto tratamento de exceções, e o fechamento adequado da conexão com o banco de dados utilizando **try-with-resources**. Além disso, foi implementada a **documentação Javadoc** para todas as classes e métodos, proporcionando uma visão clara das funcionalidades do código.

---

## 🛠️ Melhorias Implementadas no Código

### 1. **Uso de PreparedStatement**
- O código agora utiliza **PreparedStatement** para evitar **SQL Injection**, substituindo a concatenação de strings no método `verificarUsuario()`. Isso garante que os parâmetros da consulta SQL sejam passados de forma segura.

### 2. **Tratamento Adequado de Exceções**
- Corrigimos o tratamento de exceções utilizando **SQLException** e **ClassNotFoundException**, o que permite um melhor controle de erros durante a execução do código.

### 3. **Fechamento de Recursos (try-with-resources)**
- Implementamos o **try-with-resources** para garantir que a conexão com o banco de dados, os statements e os ResultSets sejam fechados automaticamente após o uso, evitando vazamentos de recursos.

### 4. **Uso de Variáveis de Ambiente para Credenciais**
- As credenciais do banco de dados agora são armazenadas em **variáveis de ambiente** (ex: `DB_USER` e `DB_PASSWORD`), aumentando a segurança e facilitando a configuração em diferentes ambientes.

---

## 📋 Código Corrigido

```java
package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe User que realiza a conexão com o banco de dados MySQL
 * e verifica as credenciais de login e senha.
 */
public class User {

    // URL de conexão, utilizando variáveis de ambiente para proteger as credenciais
    private static final String URL = "jdbc:mysql://localhost/connectiondb";
    private static final String USER = System.getenv("DB_USER");  // Variável de ambiente para o usuário
    private static final String PASSWORD = System.getenv("DB_PASSWORD");  // Variável de ambiente para a senha

    /**
     * Conecta ao banco de dados MySQL.
     * 
     * @return Um objeto {@link Connection} representando a conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro na conexão com o banco de dados.
     */
    public Connection conectarBD() throws SQLException {
        Connection conn;
        try {
            // Carregar o driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Lidar com erro de driver não encontrado
            throw new SQLException("Driver MySQL não encontrado.", e);
        }
        return conn;
    }

    /**
     * Nome do usuário após o login bem-sucedido.
     */
    public String nome = "";

    /**
     * Resultado da verificação de login.
     * Será {@code true} se o login for bem-sucedido, {@code false} caso contrário.
     */
    public boolean result = false;

    /**
     * Verifica se o login do usuário e a senha são válidos.
     * 
     * @param login O nome de usuário fornecido.
     * @param senha A senha fornecida.
     * @return {@code true} se o login e senha forem válidos, {@code false} caso contrário.
     * @throws SQLException Se ocorrer um erro ao consultar o banco de dados.
     */
    public boolean verificarUsuario(String login, String senha) throws SQLException {
        String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";
        
        // Usando try-with-resources para garantir o fechamento dos recursos (conexão, PreparedStatement e ResultSet)
        try (Connection conn = conectarBD();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            // Definir os parâmetros para a consulta SQL
            ps.setString(1, login);
            ps.setString(2, senha);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Login válido, salvar o nome do usuário
                    result = true;
                    nome = rs.getString("nome");
                } else {
                    // Login ou senha inválidos
                    result = false;
                }
            }
        } catch (SQLException e) {
            // Tratamento de exceção no caso de erro de consulta ou conexão
            throw new SQLException("Erro ao verificar o usuário no banco de dados.", e);
        }
        return result;
    }
}

📖 Implementação do Javadoc
A documentação Javadoc foi gerada para todas as classes e métodos do projeto, descrevendo o comportamento de cada função e as variáveis públicas utilizadas.

Como Gerar a Documentação Javadoc
Para gerar a documentação Javadoc, execute o seguinte comando no terminal do VS Code:

javadoc -d ./docs -sourcepath ./src -subpackages Login
Isso criará a pasta docs com todos os arquivos HTML da documentação Javadoc.

Como Acessar a Documentação
Após gerar o Javadoc, você pode acessar a documentação abrindo o arquivo index.html na pasta docs em um navegador.

---

### 📂 **Explicação do `README.md`**:

1. **Melhorias Implementadas**:
   - Detalha as correções que foram aplicadas no código, como o uso de **PreparedStatement**, tratamento de exceções e variáveis de ambiente.

2. **Código Corrigido**:
   - Exibe o código completo com todas as melhorias implementadas e os comentários Javadoc.

3. **Geração e Acesso ao Javadoc**:
   - Explica como gerar a documentação Javadoc e como acessá-la na pasta `docs`.


---
