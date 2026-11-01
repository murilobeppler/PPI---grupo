package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReceitaDAO;
import model.Receita;
import util.ConnectionFactory;

@MultipartConfig
@WebServlet("/ConsultarReceita")
public class ConsultarReceitaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ReceitaDAO receitaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        receitaDAO = new ReceitaDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");

        if (codigo != null && !codigo.isEmpty()) {
            Receita receita = receitaDAO.buscarPorCodigo(codigo);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (receita != null) {
                out.println("<html><body><h2>Receita Encontrada:</h2>");
                out.println("<p>Nome: " + receita.getNome() + "</p>");
                out.println("<p>Tipo: " + receita.getTipo() + "</p>");
                out.println("<p>Resumo: " + receita.getResumo() + "</p>");
                out.println("</body></html>");
            } else {
                out.println("<h2>Receita não encontrada com este código.</h2>");
            }
        }
    }

    @Override
    public void destroy() {
        ConnectionFactory.fecharConexao();
        receitaDAO = null;
        super.destroy();
    }
}