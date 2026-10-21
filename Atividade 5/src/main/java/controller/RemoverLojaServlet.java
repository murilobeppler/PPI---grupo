package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LojaDAO;

@MultipartConfig
@WebServlet("/RemoverLoja")
public class RemoverLojaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("loja_id");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            LojaDAO dao = new LojaDAO();
            dao.remove(id);

            response.sendRedirect("formulario_loja.html?removida=true");
        }
    }
}