package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GaleriaDAO;
import model.Galeria;

@MultipartConfig
@WebServlet("/ConsultarGaleria")
public class ConsultarGaleriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String receitaIdStr = request.getParameter("receita_id");

        if (receitaIdStr != null && !receitaIdStr.isEmpty()) {
            int receitaId = Integer.parseInt(receitaIdStr);
            GaleriaDAO dao = new GaleriaDAO();
            List<Galeria> imagens = dao.listarPorReceitaId(receitaId);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html><body><h2>Galeria da Receita ID: " + receitaId + "</h2>");
            if (!imagens.isEmpty()) {
                out.println("<ul>");
                for (Galeria img : imagens) {
                    out.println("<li>ID da Imagem: " + img.getId() + " | Caminho: " + img.getUrlImagem() + "</li>");
                }
                out.println("</ul>");
            } else {
                out.println("<p>Nenhuma imagem encontrada para esta receita.</p>");
            }
            out.println("<a href='formulario_galeria.html'>Voltar</a>");
            out.println("</body></html>");
        }
    }
}