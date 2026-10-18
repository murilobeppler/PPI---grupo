package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/criarte?useSSL=false&useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root"; // ATUALIZAR O USUARIO
    private static final String PASSWORD = "12345678"; // ATUALIZAR A SENHA

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do MySQL não encontrado! Adicione o conector (arquivo .jar) ao projeto.",
                    e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco de dados: " + e.getMessage(), e);
        }
    }
}