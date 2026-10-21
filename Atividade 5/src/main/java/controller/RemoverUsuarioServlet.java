package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;

@MultipartConfig
@WebServlet("/RemoverUsuario")
public class RemoverUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String usuarioIdStr = request.getParameter("usuario_id");

        if (usuarioIdStr != null && !usuarioIdStr.isEmpty()) {
            int id = Integer.parseInt(usuarioIdStr);
            UsuarioDAO usuarioDao = new UsuarioDAO();

            usuarioDao.remove(id);

            response.sendRedirect("formulario_usuario.html?removida=true");
        }
    }
}