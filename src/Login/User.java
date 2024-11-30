package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    public Connection conectarBD() {
        Connection conn = null;
<<<<<<< Updated upstream

        try {
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://localhost/connectiondb?user=admin&password=master";
            conn = DriverManager.getConnection(url);
        } catch (Exception e) { 
=======
        try {
            Class.forName("com.mysql.Driver.Manager").newInstance();  // Nodo 1
            String url = "jdbc:mysql://localhost/connectiondb?user=admin&password=master";  // Nodo 2
            conn = DriverManager.getConnection(url);  // Nodo 3
        } catch (Exception e) {  // Nodo 4
>>>>>>> Stashed changes
            // SEM TRATAMENTO 
        }
        return conn;  // Nodo 5
    }

<<<<<<< Updated upstream
    public String nome = "";
    public boolean result = false;

    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();
        // INSTRUÇÃO SQL
        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "'";
        sql += " and senha = '" + senha + "';";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }

        } catch (Exception e) {
            
=======
    public String nome = "";  // Nodo 6
    public boolean result = false;  // Nodo 7

    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();  // Nodo 8
        sql += "select nome from usuarios ";  // Nodo 9
        sql += "where login = '" + login + "'";  // Nodo 10
        sql += " and senha = '" + senha + "';";  // Nodo 11

        try {  // Nodo 12
            Statement st = conn.createStatement();  // Nodo 13
            ResultSet rs = st.executeQuery(sql);  // Nodo 14
            if (rs.next()) {  // Nodo 15
                result = true;  // Nodo 16
                nome = rs.getString("nome");  // Nodo 17
            }
        } catch (Exception e) {  // Nodo 18
            // SEM TRATAMENTO 
>>>>>>> Stashed changes
        }
        return result;  // Nodo 19
    }
}
