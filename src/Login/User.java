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
