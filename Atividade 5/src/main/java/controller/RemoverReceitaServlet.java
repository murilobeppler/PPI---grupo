package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReceitaDAO;

@MultipartConfig
@WebServlet("/RemoverReceita")
public class RemoverReceitaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");

        if (codigo != null && !codigo.isEmpty()) {
            ReceitaDAO dao = new ReceitaDAO();
            dao.remove(codigo);

            response.sendRedirect("formulario_receita.html?removida=true");
        }
    }
}