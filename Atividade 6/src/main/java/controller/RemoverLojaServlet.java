package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LojaDAO;
import util.ConnectionFactory;

@MultipartConfig
@WebServlet("/RemoverLoja")
public class RemoverLojaServlet extends HttpServlet {
    private LojaDAO lojaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        lojaDAO = new LojaDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("loja_id");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            lojaDAO.remove(id);

            response.sendRedirect("formulario_loja.html?removida=true");
        }
    }

    @Override
    public void destroy(){
        ConnectionFactory.fecharConexao();
        lojaDAO = null;
        super.destroy();
    }
}