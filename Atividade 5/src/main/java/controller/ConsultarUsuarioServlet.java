package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

@MultipartConfig
@WebServlet("/ConsultarUsuario")
public class ConsultarUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String usuarioIdStr = request.getParameter("usuario_id");

        if (usuarioIdStr != null && !usuarioIdStr.isEmpty()) {
            int id = Integer.parseInt(usuarioIdStr);

            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuario = usuarioDao.buscarPorId(id);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (usuario != null) {
                out.println("<html><head><title>Usuário Encontrado</title></head><body>");
                out.println("<h2>Dados do Usuário:</h2>");
                out.println("<p><strong>ID:</strong> " + usuario.getId() + "</p>");
                out.println("<p><strong>Nome:</strong> " + usuario.getNome() + "</p>");
                out.println("<p><strong>Email:</strong> " + usuario.getEmail() + "</p>");
                out.println("<p><strong>Usuário:</strong> " + usuario.getUsername() + "</p>");
                out.println("<br/><a href='formulario_usuario.html'>Voltar</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><head><title>Não Encontrado</title></head><body>");
                out.println("<h2>Usuário não encontrado!</h2>");
                out.println("<p>O ID " + id + " não consta na base de dados.</p>");
                out.println("<br/><a href='formulario_usuario.html'>Voltar</a>");
                out.println("</body></html>");
            }
        } else {
            response.sendRedirect("formulario_usuario.html");
        }
    }
}
