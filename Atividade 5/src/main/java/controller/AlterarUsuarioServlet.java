package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

@MultipartConfig
@WebServlet("/AlterarUsuario")
public class AlterarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        UsuarioDAO usuarioDao = new UsuarioDAO();

        try {
            int id = Integer.parseInt(request.getParameter("usuario_id"));

            Usuario usuario = new Usuario();
            usuario.setId(id);

            usuario.setNome(request.getParameter("nome"));
            usuario.setUsername(request.getParameter("username"));
            usuario.setEmail(request.getParameter("email"));

            usuarioDao.altera(usuario);

            response.sendRedirect("formulario_usuario.html?alterada=true");

            response.sendRedirect("formulario_usuario.html");

        } catch (

        Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }
}
