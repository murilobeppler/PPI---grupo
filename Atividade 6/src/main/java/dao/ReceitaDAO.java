package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Receita;
import util.ConnectionFactory;

public class ReceitaDAO {
    private Connection connection;

    public ReceitaDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Receita receita) {
        String sql = "INSERT INTO criarte.receita (usuario_id, codigo, nome, tipo, preco, descricao, resumo, materiais, url_video, url_pdf, criado_em) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, receita.getUsuarioId());
            stmt.setString(2, receita.getCodigo());
            stmt.setString(3, receita.getNome());
            stmt.setString(4, receita.getTipo());
            stmt.setDouble(5, receita.getPreco());
            stmt.setString(6, receita.getDescricao());
            stmt.setString(7, receita.getResumo());
            stmt.setString(8, receita.getMateriais());
            stmt.setString(9, receita.getUrlVideo());
            stmt.setString(10, receita.getUrlPdf());
            stmt.setTimestamp(11, receita.getCriadoEm());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar receita: " + e.getMessage());
        }
    }

    public Receita buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM criarte.receita WHERE codigo = ?";
        Receita receita = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                receita = new Receita();
                receita.setId(rs.getInt("id"));
                receita.setUsuarioId(rs.getInt("usuario_id"));
                receita.setCodigo(rs.getString("codigo"));
                receita.setNome(rs.getString("nome"));
                receita.setTipo(rs.getString("tipo"));
                receita.setPreco(rs.getDouble("preco"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setResumo(rs.getString("resumo"));
                receita.setMateriais(rs.getString("materiais"));
                receita.setUrlVideo(rs.getString("url_video"));
                receita.setUrlPdf(rs.getString("url_pdf"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar receita: " + e.getMessage());
        }
        return receita;
    }

    public void altera(Receita receita) {
        String sql = "UPDATE criarte.receita SET usuario_id=?, nome=?, tipo=?, preco=?, descricao=?, resumo=?, materiais=?, url_video=?, url_pdf=?, criado_em=? WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, receita.getUsuarioId());
            stmt.setString(2, receita.getNome());
            stmt.setString(3, receita.getTipo());
            stmt.setDouble(4, receita.getPreco());
            stmt.setString(5, receita.getDescricao());
            stmt.setString(6, receita.getResumo());
            stmt.setString(7, receita.getMateriais());
            stmt.setString(8, receita.getUrlVideo());
            stmt.setString(9, receita.getUrlPdf());
            stmt.setTimestamp(10, receita.getCriadoEm());
            stmt.setString(11, receita.getCodigo());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar receita: " + e.getMessage());
        }
    }

    public void remove(String codigo) {
        String sql = "DELETE FROM criarte.receita WHERE codigo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover receita: " + e.getMessage());
        }
    }
}