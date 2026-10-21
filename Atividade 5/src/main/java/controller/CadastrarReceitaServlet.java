package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReceitaDAO;
import model.Receita;

@MultipartConfig
@WebServlet("/CadastrarReceita")
public class CadastrarReceitaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        try {
            Receita receita = new Receita();
            receita.setCodigo(request.getParameter("codigo"));
            receita.setNome(request.getParameter("nome"));
            receita.setTipo(request.getParameter("tipo"));
            receita.setUsuarioId(Integer.parseInt(request.getParameter("usuario_id")));
            
            String precoStr = request.getParameter("preco");
            if(precoStr != null && !precoStr.isEmpty()) {
                receita.setPreco(Double.parseDouble(precoStr));
            }

            receita.setDescricao(request.getParameter("descricao"));
            receita.setResumo(request.getParameter("resumo"));
            receita.setMateriais(request.getParameter("materiais"));
            receita.setUrlVideo(request.getParameter("url_video"));
            
            receita.setUrlPdf("caminho/do/pdf.pdf"); 

            String criadoEmStr = request.getParameter("criado_em");
            if (criadoEmStr != null && !criadoEmStr.isEmpty()) {
                receita.setCriadoEm(Timestamp.valueOf(LocalDateTime.parse(criadoEmStr)));
            }

            ReceitaDAO dao = new ReceitaDAO();
            dao.adiciona(receita);

            response.sendRedirect("formulario_receita.html?sucesso=true");
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }
}