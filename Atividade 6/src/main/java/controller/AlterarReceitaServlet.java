package controller;

import java.io.IOException;
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
@WebServlet("/AlterarReceita")
public class AlterarReceitaServlet extends HttpServlet {
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
        request.setCharacterEncoding("UTF-8");
        try {
            String codigo = request.getParameter("codigo");
            Receita receita = receitaDAO.buscarPorCodigo(codigo);

            if (receita != null) {
                if (request.getParameter("nome") != null && !request.getParameter("nome").isEmpty())
                    receita.setNome(request.getParameter("nome"));
                if (request.getParameter("tipo") != null)
                    receita.setTipo(request.getParameter("tipo"));
                if (request.getParameter("resumo") != null)
                    receita.setResumo(request.getParameter("resumo"));
                // Outros campos devem ser validados e inseridos aqui

                receitaDAO.altera(receita);
                response.sendRedirect("formulario_receita.html?alterada=true");
            } else {
                response.sendRedirect("formulario_receita.html?erro=naoencontrada");
            }
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    @Override
    public void destroy(){
        ConnectionFactory.fecharConexao();
        receitaDAO = null;
        super.destroy();
    }
}