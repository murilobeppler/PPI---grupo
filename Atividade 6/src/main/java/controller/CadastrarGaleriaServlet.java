package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GaleriaDAO;
import model.Galeria;
import util.ConnectionFactory;

@MultipartConfig
@WebServlet("/CadastrarGaleria")
public class CadastrarGaleriaServlet extends HttpServlet {
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
            Galeria galeria = new Galeria();
            galeria.setReceitaId(Integer.parseInt(request.getParameter("receita_id")));

            galeria.setUrlImagem("uploads/galeria/" + System.currentTimeMillis() + "_img.png");

            galeriaDAO.adiciona(galeria);

            response.sendRedirect("formulario_galeria.html?sucesso=true");
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