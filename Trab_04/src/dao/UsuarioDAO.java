package dao;

import main.java.model.Usuario;
import main.java.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // Função de cadastrar
    public void adiciona(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, username, senha) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, "Pedro");
            stmt.setString(2, "pedrinho@mail.com");
            stmt.setString(3, "@pedro");
            stmt.setString(4, "12345678");

            stmt.execute();
            stmt.close();

            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir: " + e.getMessage());
        }
    }

    // Função de consulta
    public void listarTodos() {
        String sql = "SELECT * FROM usuario";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String username = rs.getString("username");
                
                System.out.println(id + " :: " + nome + " :: " + email + " :: " + username);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage());
        }
    }

    // Função de alterar
    public void altera(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, email=?, username=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, "Joana");
            stmt.setString(2, "joana@mail.com");
            stmt.setString(3, "@Joana_Darc");
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Usuário alterado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar: " + e.getMessage());
        }
    }

    // Função de remover
    public void remove(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Usuário removido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover: " + e.getMessage());
        }
    }
}
