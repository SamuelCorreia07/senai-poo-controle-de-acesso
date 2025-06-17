package com.senai.view.usuario;

import com.senai.control.usuario.AQVController;
import com.senai.model.usuario.AQV;

import java.util.List;
import java.util.Scanner;

public class AqvView {
    private final AQVController controller = new AQVController();
    private final Scanner scanner = new Scanner(System.in);

    private void menu() {
        int opcao;
        do {
            System.out.print("""
                
                ===================== MENU AQV =====================
                1 - Cadastrar AQV
                2 - Listar AQVs
                3 - Atualizar AQV
                4 - Remover AQV
                0 - Sair
                ====================================================
                Escolha uma opção: """);

            opcao = lerInt();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> remover();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Nome do AQV: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Login: ");
        String login = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        String resultado = controller.inserirAQV(nome, login, senha);
        System.out.println(resultado);
    }

    private void listar() {
        List<AQV> lista = controller.listarAQVs();
        if (lista.isEmpty()) {
            System.out.println("Nenhum AQV registrado.");
            return;
        }

        System.out.println("\n======= LISTA DE AQVs =======");
        for (AQV a : lista) {
            System.out.printf("""
            ID: %d
            Nome: %s
            Login: %s
            Senha: %s
            ---------------------------------------
            """, a.getId(), a.getNome(), a.getLogin(), a.getSenha());
        }
    }

    private void atualizar() {
        listar();
        System.out.print("ID do AQV a atualizar: ");
        int id = lerInt();

        AQV existente = controller.buscarPorId(id);
        if (existente == null) {
            System.out.println("AQV não encontrado.");
            return;
        }

        System.out.print("Novo nome do AQV: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Novo login: ");
        String login = scanner.nextLine().trim();

        System.out.print("Nova senha: ");
        String senha = scanner.nextLine().trim();

        String resultado = controller.atualizarAQV(id, nome, login, senha);
        System.out.println(resultado);
    }

    private void remover() {
        listar();
        System.out.print("ID do AQV a remover: ");
        int id = lerInt();

        System.out.print("Tem certeza que deseja remover este AQV? (S/N): ");
        String confirmacao = scanner.nextLine().trim().toUpperCase();

        if (confirmacao.equals("S")) {
            String resultado = controller.removerAQV(id);
            System.out.println(resultado);
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Digite novamente: ");
            }
        }
    }
}