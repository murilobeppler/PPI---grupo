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
@WebServlet("/RemoverGaleria")
public class RemoverGaleriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GaleriaDAO galeriaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.galeriaDAO = new GaleriaDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String receitaIdStr = request.getParameter("receita_id");

        if (receitaIdStr != null && !receitaIdStr.isEmpty()) {
            int receitaId = Integer.parseInt(receitaIdStr);
            this.galeriaDAO.removePorReceitaId(receitaId);
            response.sendRedirect("formulario_galeria.html?removida=true");
        }
    }

    @Override
    public void destroy() {
        ConnectionFactory.fecharConexao();
        this.galeriaDAO = null;
        super.destroy();
    }
}