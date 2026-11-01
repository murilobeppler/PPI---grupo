package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GaleriaDAO;
import util.ConnectionFactory;

@MultipartConfig
@WebServlet("/AlterarGaleria")
public class AlterarGaleriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GaleriaDAO galeriaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        galeriaDAO = new GaleriaDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int receitaId = Integer.parseInt(request.getParameter("receita_id"));
            String novaUrlMockada = "uploads/galeria/" + System.currentTimeMillis() + "_img_alterada.png";

            galeriaDAO.alteraPorReceitaId(receitaId, novaUrlMockada);

            response.sendRedirect("formulario_galeria.html?alterada=true");
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        ConnectionFactory.fecharConexao();
        galeriaDAO = null;
        super.destroy();
    }
}