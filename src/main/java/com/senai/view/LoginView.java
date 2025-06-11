package com.senai.view;

import com.senai.control.LoginController;
import com.senai.model.usuario.Administrador;
import com.senai.model.usuario.Usuario;
import com.senai.util.CriptografiaUtil;

import java.util.Optional;
import java.util.Scanner;

public class LoginView {
    private final Scanner scanner = new Scanner(System.in);
    private final LoginController controller = new LoginController();

    public Optional<Usuario> menuLoginView() {
        String login, senha;

        System.out.println("""
                
                _____________________________________________________________
                |                    LOGIN DO SISTEMA                       |
                |___________________________________________________________|
                """);

        System.out.print("\tLogin: ");
        login = scanner.nextLine().trim();

        System.out.print("\tSenha: ");
        senha = scanner.nextLine().trim();

        if (login.equals("adm") && senha.equals("1234")) {
            System.out.println("\n\tLogin realizado com sucesso!\n");
            return Optional.of(new Administrador(0, "adm", "adm", "1234"));
        }

        Optional<Usuario> usuario = controller.autenticar(login, CriptografiaUtil.hash(senha));

        if (usuario.isEmpty()) {
            System.out.println("\n\tCredenciais inválidas. Tente novamente.\n");
            menuLoginView();
        } else {
            System.out.println("\n\tLogin realizado com sucesso!\n");
        }

        return usuario;
    }
}
