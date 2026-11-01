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
import util.ConnectionFactory;

@MultipartConfig
@WebServlet("/AlterarUsuario")
public class AlterarUsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("usuario_id"));

            Usuario usuario = new Usuario();
            usuario.setId(id);

            usuario.setNome(request.getParameter("nome"));
            usuario.setUsername(request.getParameter("username"));
            usuario.setEmail(request.getParameter("email"));

            usuarioDAO.altera(usuario);

            response.sendRedirect("formulario_usuario.html?alterada=true");

        } catch (

        Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        ConnectionFactory.fecharConexao();
        usuarioDAO = null;
        super.destroy();
    }
}
