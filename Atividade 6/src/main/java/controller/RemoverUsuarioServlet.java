package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import util.ConnectionFactory;

@MultipartConfig
@WebServlet("/RemoverUsuario")
public class RemoverUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
        String usuarioIdStr = request.getParameter("usuario_id");

        if (usuarioIdStr != null && !usuarioIdStr.isEmpty()) {
            int id = Integer.parseInt(usuarioIdStr);

            usuarioDAO.remove(id);

            response.sendRedirect("formulario_usuario.html?removida=true");
        }
    }

    @Override
    public void destroy(){
        ConnectionFactory.fecharConexao();
        usuarioDAO = null;
        super.destroy();
    }
}