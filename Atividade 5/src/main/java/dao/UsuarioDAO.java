package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;
import util.ConnectionFactory;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // Função de cadastrar
    public void adiciona(Usuario usuario) {
        String sql = "INSERT INTO criarte.usuario (nome, email, username, senha, sexo, contato, aceita_termos, foto_perfil) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getUsername());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getSexo());
            stmt.setString(6, usuario.getContato());
            stmt.setBoolean(7, usuario.isAceitaTermos());
            stmt.setString(8, usuario.getFotoPerfil());

            stmt.execute();
            stmt.close();

            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir: " + e.getMessage());
        }
    }

    // Função de listar todos
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
        String sql = "UPDATE criarte.usuario SET nome=?, email=?, username=?, sexo=?, contato=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getUsername());
            stmt.setString(4, usuario.getSexo());
            stmt.setString(5, usuario.getContato());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Usuário alterado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar: " + e.getMessage());
        }
    }

    // Função de remover
    public void remove(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();

            System.out.println("Usuário removido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover: " + e.getMessage());
        }
    }

    // Função de consulta por id
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsername(rs.getString("username"));
                usuario.setSexo(rs.getString("sexo"));
                usuario.setContato(rs.getString("contato"));
                usuario.setFotoPerfil(rs.getString("foto_perfil"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar por ID: " + e.getMessage());
        }

        return usuario;
    }
}
