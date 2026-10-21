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
@WebServlet("/CadastrarUsuario")
public class CadastrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        UsuarioDAO usuarioDao = new UsuarioDAO();

        try {
            String nome = request.getParameter("nome");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String sexo = request.getParameter("sexo");
            String contato = request.getParameter("contato");
            boolean termos = request.getParameter("aceita_termos") != null;

            // função do arquivo pdf
            String uploadPath = getServletContext().getRealPath("") + java.io.File.separator + "uploads";
            java.io.File uploadDir = new java.io.File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();


            // função foto
            String urlFotoUsuario = null;
            javax.servlet.http.Part fotoUsuarioPart = request.getPart("foto_perfil");
            if (fotoUsuarioPart != null && fotoUsuarioPart.getSize() > 0) {
                String fileName = System.currentTimeMillis() + "_usuario_" + fotoUsuarioPart.getSubmittedFileName();
                fotoUsuarioPart.write(uploadPath + java.io.File.separator + fileName);
                urlFotoUsuario = "uploads/" + fileName;
            }

            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(nome);
            novoUsuario.setUsername(username);
            novoUsuario.setEmail(email);
            novoUsuario.setSenha(senha);
            novoUsuario.setSexo(sexo);
            novoUsuario.setContato(contato);
            novoUsuario.setAceitaTermos(termos);
            novoUsuario.setFotoPerfil(urlFotoUsuario);

            usuarioDao.adiciona(novoUsuario);

             response.sendRedirect("formulario_usuario.html?sucesso=true");

        } catch (Exception e) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("Erro no processamento: " + e.getMessage());
        }
    }
}
