package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Loja;
import util.ConnectionFactory;

public class LojaDAO {
    private Connection connection;

    public LojaDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Loja loja) {
        String sql = "INSERT INTO criarte.loja (usuario_id, nome, descricao, foto_perfil, ativo, apagado, criado_em) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, loja.getUsuarioId());
            stmt.setString(2, loja.getNome());
            stmt.setString(3, loja.getDescricao());
            stmt.setString(4, loja.getFotoPerfil());
            stmt.setBoolean(5, loja.isAtivo());
            stmt.setBoolean(6, loja.isApagado());
            stmt.setTimestamp(7, loja.getCriadoEm());

            stmt.execute();
            stmt.close();
            
            System.out.println("Loja cadastrada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar loja: " + e.getMessage());
        }
    }

    public Loja buscarPorId(int id) {
        String sql = "SELECT * FROM criarte.loja WHERE id = ?";
        Loja loja = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                loja = new Loja();
                loja.setId(rs.getInt("id"));
                loja.setUsuarioId(rs.getInt("usuario_id"));
                loja.setNome(rs.getString("nome"));
                loja.setDescricao(rs.getString("descricao"));
                loja.setFotoPerfil(rs.getString("foto_perfil"));
                loja.setAtivo(rs.getBoolean("ativo"));
                loja.setApagado(rs.getBoolean("apagado"));
                loja.setCriadoEm(rs.getTimestamp("criado_em"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar loja: " + e.getMessage());
        }
        return loja;
    }

    public void altera(Loja loja) {
        String sql = "UPDATE criarte.loja SET usuario_id=?, nome=?, descricao=?, foto_perfil=?, ativo=?, criado_em=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, loja.getUsuarioId());
            stmt.setString(2, loja.getNome());
            stmt.setString(3, loja.getDescricao());
            stmt.setString(4, loja.getFotoPerfil());
            stmt.setBoolean(5, loja.isAtivo());
            stmt.setTimestamp(6, loja.getCriadoEm());
            stmt.setInt(7, loja.getId());

            stmt.executeUpdate();
            stmt.close();
            System.out.println("Loja alterada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar loja: " + e.getMessage());
        }
    }

    public void remove(int id) {
        String sql = "DELETE FROM criarte.loja WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Loja removida com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover loja: " + e.getMessage());
        }
    }
}