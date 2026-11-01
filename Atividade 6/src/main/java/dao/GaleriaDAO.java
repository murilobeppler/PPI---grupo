package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Galeria;
import util.ConnectionFactory;

public class GaleriaDAO {
    private Connection connection;

    public GaleriaDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Galeria galeria) {
        String sql = "INSERT INTO criarte.galeria (receita_id, url_imagem) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, galeria.getReceitaId());
            stmt.setString(2, galeria.getUrlImagem());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar imagem na galeria: " + e.getMessage());
        }
    }

    public List<Galeria> listarPorReceitaId(int receitaId) {
        String sql = "SELECT * FROM criarte.galeria WHERE receita_id = ?";
        List<Galeria> imagens = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, receitaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Galeria galeria = new Galeria();
                galeria.setId(rs.getInt("id"));
                galeria.setReceitaId(rs.getInt("receita_id"));
                galeria.setUrlImagem(rs.getString("url_imagem"));
                galeria.setApagado(rs.getBoolean("apagado"));
                imagens.add(galeria);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar galeria: " + e.getMessage());
        }
        return imagens;
    }

    public void alteraPorReceitaId(int receitaId, String novaUrlImagem) {
        String sql = "UPDATE criarte.galeria SET url_imagem=? WHERE receita_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, novaUrlImagem);
            stmt.setInt(2, receitaId);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar galeria: " + e.getMessage());
        }
    }

    public void removePorReceitaId(int receitaId) {
        String sql = "DELETE FROM criarte.galeria WHERE receita_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, receitaId);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover galeria: " + e.getMessage());
        }
    }
}