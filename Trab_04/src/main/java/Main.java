package main.java;

import dao.UsuarioDAO;
import main.java.model.Usuario;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioTeste = new Usuario();

        System.out.println("\n--- Testando Inserção ---");
        dao.adiciona(usuarioTeste);

        System.out.println("\n--- Testando Listagem ---");
        dao.listarTodos();

        usuarioTeste.setId(6);

        System.out.println("\n--- Testando Alteração ---");
        dao.altera(usuarioTeste);

        System.out.println("\n--- Testando Listagem ---");
        dao.listarTodos();

        System.out.println("\n--- Testando Remoção ---");
        dao.remove(usuarioTeste);

        System.out.println("\n--- Listagem após remoção ---");
        dao.listarTodos();
    }
}