package com.senai.view;
import com.senai.control.LoginController;
import com.senai.model.usuario.Usuario;
import com.senai.util.CriptografiaUtil;

import java.util.Optional;
import java.util.Scanner;

public class LoginView {
    private final Scanner scanner = new Scanner(System.in);
    private final LoginController controller = new LoginController();

    public Optional<Usuario> exibirLogin() {
        System.out.println("\n===== LOGIN DO SISTEMA =====");
        System.out.print("\tLogin: ");
        String login = scanner.nextLine();

        System.out.print("\tSenha: ");
        String senha = scanner.nextLine();

        Optional<Usuario> usuario = controller.autenticar(login, CriptografiaUtil.hash(senha));
        if (usuario.isEmpty()) {
            System.out.println("\nCredenciais inválidas. Tente novamente.\n");
        }
        return usuario;
    }
}