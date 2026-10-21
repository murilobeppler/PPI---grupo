package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LojaDAO;
import model.Loja;

@MultipartConfig
@WebServlet("/AlterarLoja")
public class AlterarLojaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("loja_id"));
            LojaDAO dao = new LojaDAO();
            Loja loja = dao.buscarPorId(id);

            if (loja != null) {
                if (request.getParameter("usuario_id") != null && !request.getParameter("usuario_id").isEmpty())
                    loja.setUsuarioId(Integer.parseInt(request.getParameter("usuario_id")));

                if (request.getParameter("nome") != null)
                    loja.setNome(request.getParameter("nome"));
                if (request.getParameter("descricao") != null)
                    loja.setDescricao(request.getParameter("descricao"));
                if (request.getParameter("ativo") != null)
                    loja.setAtivo("1".equals(request.getParameter("ativo")));

                String criadoEmStr = request.getParameter("criado_em");
                if (criadoEmStr != null && !criadoEmStr.isEmpty()) {
                    loja.setCriadoEm(Timestamp.valueOf(LocalDateTime.parse(criadoEmStr)));
                }

                dao.altera(loja);
                response.sendRedirect("formulario_loja.html?alterada=true");
            } else {
                response.sendRedirect("formulario_loja.html?erro=naoencontrada");
            }
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }
}