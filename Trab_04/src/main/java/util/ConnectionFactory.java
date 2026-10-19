package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/criarte?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root"; // ATUALIZAR O USUARIO
    private static final String PASSWORD = "12345678"; // ATUALIZAR A SENHA

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do MySQL não encontrado! Adicione o conector (arquivo .jar) ao projeto.",
                    e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco de dados: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Tentando conectar ao banco de dados...");
        try {
            Connection conexao = ConnectionFactory.getConnection();
            System.out.println("Conexão realizada com sucesso!");
            conexao.close();
        } catch (Exception e) {
            System.out.println("Falha ao conectar. Verifique seu banco, usuário e senha.");
            e.printStackTrace();
        }
    }
}