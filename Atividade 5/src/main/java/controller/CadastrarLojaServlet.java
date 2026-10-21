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
@WebServlet("/CadastrarLoja")
public class CadastrarLojaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            Loja novaLoja = new Loja();
            novaLoja.setUsuarioId(Integer.parseInt(request.getParameter("usuario_id")));
            novaLoja.setNome(request.getParameter("nome"));
            novaLoja.setDescricao(request.getParameter("descricao"));

            String criadoEmStr = request.getParameter("criado_em");
            if (criadoEmStr != null && !criadoEmStr.isEmpty()) {
                LocalDateTime ldt = LocalDateTime.parse(criadoEmStr);
                novaLoja.setCriadoEm(Timestamp.valueOf(ldt));
            } else {
                novaLoja.setCriadoEm(new Timestamp(System.currentTimeMillis()));
            }

            LojaDAO dao = new LojaDAO();
            dao.adiciona(novaLoja);

            response.sendRedirect("formulario_loja.html?sucesso=true");
        } catch (Exception e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }
}