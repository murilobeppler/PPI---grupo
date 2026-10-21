package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GaleriaDAO;

@MultipartConfig
@WebServlet("/RemoverGaleria")
public class RemoverGaleriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String receitaIdStr = request.getParameter("receita_id");

        if (receitaIdStr != null && !receitaIdStr.isEmpty()) {
            int receitaId = Integer.parseInt(receitaIdStr);
            GaleriaDAO dao = new GaleriaDAO();

            dao.removePorReceitaId(receitaId);

            response.sendRedirect("formulario_galeria.html?removida=true");
        }
    }
}