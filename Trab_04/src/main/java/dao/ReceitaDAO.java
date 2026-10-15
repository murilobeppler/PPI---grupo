package main.java.dao;

import java.sql.*;
import main.java.model.Receita;
import main.java.util.ConnectionFactory;

public class ReceitaDAO {

    public boolean salvar(Receita receita) {

        String sqlReceita = "INSERT INTO receita (codigo, nome, tipo_artesanato, criador, descricao, materiais, data_criacao, preco, caminho_pdf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlImagem = "INSERT INTO receita_imagem (id_receita, caminho_foto) VALUES (?, ?)";

        Connection conn = null; // Mantém e gerencia a conexão física com o MySQL
        PreparedStatement stmtReceita = null; // Compila e executa a instrução SQL da receita
        PreparedStatement stmtImagem = null; // Compila e executa a instrução SQL das imagens
        ResultSet rs = null; // Armazena a tabela de resultados devolvida pelo banco (neste caso, o ID
                             // gerado)

        try {
            // Conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            stmtReceita = conn.prepareStatement(sqlReceita, Statement.RETURN_GENERATED_KEYS);
            stmtReceita.setString(1, receita.getCodigo());
            stmtReceita.setString(2, receita.getNome());
            stmtReceita.setString(3, receita.getTipoArtesanato());
            stmtReceita.setString(4, receita.getCriador());
            stmtReceita.setString(5, receita.getDescricao());
            stmtReceita.setString(6, receita.getMateriais());
            stmtReceita.setDate(7, Date.valueOf(receita.getDataCriacao()));
            stmtReceita.setBigDecimal(8, receita.getPreco());
            stmtReceita.setString(9, receita.getCaminhoPdf());

            int linhasAfetadas = stmtReceita.executeUpdate();

            // Se uma linha foi criada (inserida com sucesso) Uso a Id gerada e vincula as
            // imagens a receita
            if (linhasAfetadas > 0) {
                rs = stmtReceita.getGeneratedKeys();

                if (rs.next()) { // Se houver um ID gerado, recupera o ID da receita recém-inserida
                    int idReceitaGerado = rs.getInt(1);

                    receita.setId(idReceitaGerado);
                    stmtImagem = conn.prepareStatement(sqlImagem);

                    // Laço de repetição para percorrer a lista de fotos vinculadas a esta receita.
                    for (String caminhoImagem : receita.getCaminhosImagens()) {

                        stmtImagem.setInt(1, idReceitaGerado);
                        stmtImagem.setString(2, caminhoImagem);
                        stmtImagem.addBatch(); // Fila de execução e envia para o banco de dados de uma única vez
                    }

                    stmtImagem.addBatch();
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) { // DESFAZ TUDO
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            e.printStackTrace();
            return false;

        } finally { // Vai fechar os recusos e liberar memoria
            try {
                if (rs != null)
                    rs.close();
                if (stmtReceita != null)
                    stmtReceita.close();
                if (stmtImagem != null)
                    stmtImagem.close();

                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}