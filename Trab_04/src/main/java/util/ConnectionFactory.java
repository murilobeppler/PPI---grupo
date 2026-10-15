package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/NOME_DO_BANCO?useTimezone=true&serverTimezone=UTC"; // ATUALAIZAR
                                                                                                                       // O
                                                                                                                       // NOME
    private static final String USER = ""; // ATUALIZAR O USUARIO
    private static final String PASSWORD = ""; // ATUALIZAR A SENHA

    public static Connection getConnection() {
        try {
            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver"); // ATUALIZAR O DRIVE DO MYSQL EM LIB OU POW.XML
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do MySQL não encontrado! Adicione o conector (arquivo .jar) ao projeto.",
                    e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco de dados: " + e.getMessage(), e);
        }
    }
}