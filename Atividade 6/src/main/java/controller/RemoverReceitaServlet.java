package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ReceitaDAO;
import util.ConnectionFactory;

@MultipartConfig
@WebServlet("/RemoverReceita")
public class RemoverReceitaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReceitaDAO receitaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.receitaDAO = new ReceitaDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");

        if (codigo != null && !codigo.isEmpty()) {
            receitaDAO.remove(codigo);

            response.sendRedirect("formulario_receita.html?removida=true");
        }
    }

    @Override
    public void destroy() {
        ConnectionFactory.fecharConexao();
        this.receitaDAO = null;
        super.destroy();
    }

}