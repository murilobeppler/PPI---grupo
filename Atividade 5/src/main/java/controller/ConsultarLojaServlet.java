package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LojaDAO;
import model.Loja;

@MultipartConfig
@WebServlet("/ConsultarLoja")
public class ConsultarLojaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("loja_id");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            LojaDAO dao = new LojaDAO();
            Loja loja = dao.buscarPorId(id);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (loja != null) {
                out.println("<html><body><h2>Loja Encontrada:</h2>");
                out.println("<p>Nome: " + loja.getNome() + "</p>");
                out.println("<p>Descrição: " + loja.getDescricao() + "</p>");
                out.println("<p>Ativa: " + (loja.isAtivo() ? "Sim" : "Não") + "</p>");
                out.println("</body></html>");
            } else {
                out.println("<h2>Loja não encontrada.</h2>");
            }
        }
    }
}